/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author movez
 */
public class Backup {

    private static String ip;
    private static String bd;
    private static String senha;
    private static String usuario;
    private static String localsalvamento;
    private static String localpostgres;
    private static String senhac;
    private static String user;

    public static void realizaBackup() throws IOException, InterruptedException {

        try {
            // Carrega informações do arquivo de propriedades
            Properties prop = new Properties();
            prop.load(new FileInputStream("backup.properties"));
            ip = prop.getProperty("backup.ip");
            bd = prop.getProperty("backup.bd");
            senha = prop.getProperty("backup.senha");
            usuario = prop.getProperty("backup.usuario");
            localsalvamento = ("backup.salvar");
            localpostgres = prop.getProperty("backup.postgres");
            senhac = new String(Base64.getDecoder().decode(senha));
            user = new String(Base64.getDecoder().decode(usuario));
        } catch (Exception e) {
            System.err.println(e);
        }

        final List<String> comandos = new ArrayList<String>();

        //comandos.add("C:\\Program Files (x86)\\PostgreSQL\\8.4\\bin\\pg_dump.exe"); 
        //comandos.add("C:\\Program Files\\PostgresPlus\\8.4SS\\bin\\pg_dump.exe"); 
        comandos.add(localpostgres);    // esse é meu caminho  

        comandos.add("-h");
        comandos.add(ip);     //ou  comandos.add("192.168.0.1"); 
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add(user);
        comandos.add("-F");
        comandos.add("c");
        comandos.add("-b");
        comandos.add("-v");
        comandos.add("-f");

        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String dataf = formatarDate.format(data);

        comandos.add(localsalvamento + dataf + ".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
        comandos.add(bd);
        ProcessBuilder pb = new ProcessBuilder(comandos);

        pb.environment().put("PGPASSWORD", senhac);      //Somente coloque sua senha         

        try {
            final Process process = pb.start();

            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();

            process.waitFor();
            process.destroy();
            JOptionPane.showMessageDialog(null, "Backup realizado.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }
}
