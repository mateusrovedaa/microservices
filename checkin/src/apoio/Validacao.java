/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Fabricio Pretto
 */
public class Validacao {

    // Definir o workload do BCrypt. 10-31 são valores válidos.
    private static int workload = 12;
    
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    ResultSet resultadoQ = null;

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean validarCNPJ(String cnpj) {
        cnpj = Formatacao.removerFormatacao(cnpj);
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }
        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static boolean validarDataDMA(int d, int m, int a) {
        boolean correto = true;
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (a < 0 || m < 1 || m > 12) {
            correto = false;
        } else {
            // valida o dia
            if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)) {
                dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1]) {
                correto = false;
            }
        }
        return (correto);
    }

    public static boolean validarDataFormatada(String dataComFormato) {
        if (dataComFormato == null) {
            return true;
        }
        String[] data = dataComFormato.split("/");
        return (validarDataDMA(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

    public static void validarTelefone(JFormattedTextField campo) {
        if (campo.getText().trim().length() < 14) {
            Formatacao.formatarTelefone(campo);
        }
    }

    public static boolean validarSituacao(char situacao) {
        boolean ok = false;
        if (Character.toUpperCase(situacao) == 'A' || Character.toUpperCase(situacao) == 'I') {
            ok = true;
        }
        return ok;
    }

    /**
     * Método utilizado para validar se existem registros com o mesmo nome no
     * banco ou se o registro que está sendo pesquisado existe no banco de
     * dados. Para verificar se existem em duplicidade é utilizado o tipo igual
     * ao número 1, para verificar se existe um registro com a SQL pesquisada é
     * utilizado o código de tipo de número 2.
     *
     * @param sql
     * @param tabela
     * @param tipo
     * @return
     */
    public boolean validaIguais(String sql, String tabela, int tipo) {//metodo utilizado para verificar a existencia de registros duplicados no banco
        boolean ok = false;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            resultadoQ = st.executeQuery(sql);

            if (tipo == 1) {
                if (!resultadoQ.next()) {//verificar se já existe outro registro no banco
                    ok = true;
                    System.out.println("SQL Valida: " + sql);
                }
            } else if (tipo == 2) {
                if (resultadoQ.next()) {//verifica se o registro existe no banco para poder inserir
                    ok = true;
                    System.out.println("SQL Valida: " + sql);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar " + tabela + ": " + e);
            System.err.println("SQL " + tabela + ": " + sql);
        }
        return ok;
    }


    /**
     * This method can be used to generate a string representing an account
     * password suitable for storing in a database. It will be an OpenBSD-style
     * crypt(3) formatted hash string of length=60 The bcrypt workload is
     * specified in the above static variable, a value from 10 to 31. A workload
     * of 12 is a very reasonable safe default as of 2013. This automatically
     * handles secure 128-bit salt generation and storage within the hash.
     *
     * @param password_plaintext The account's plaintext password as provided
     * during account creation, or when changing an account's password.
     * @return String - a string of length 60 that is the bcrypt hashed password
     * in crypt(3) format.
     */
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }

    /**
     * This method can be used to verify a computed hash from a plaintext (e.g.
     * during a login request) with that of a stored hash from a database. The
     * password hash from the database must be passed as the second variable.
     *
     * @param password_plaintext The account's plaintext password, as provided
     * during a login request
     * @param stored_hash The account's stored password hash, retrieved from the
     * authorization database
     * @return boolean - true if the password matches the password of the stored
     * hash, false otherwise
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
}
