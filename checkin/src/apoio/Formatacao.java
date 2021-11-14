package apoio;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.*;
import javax.swing.text.*;

public class Formatacao {

    static DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static JFormattedTextField getFormatado(String formato) {
        JFormattedTextField campoFormatado = null;
        MaskFormatter format = new MaskFormatter();

        format.setPlaceholderCharacter(' ');
        format.setValueContainsLiteralCharacters(false);

        try {
            format.setMask(formato);
            campoFormatado = new JFormattedTextField(format);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return campoFormatado;
    }

    public static void formatarDecimal(JTextField campo) {
        campo.setText(df.format(Double.parseDouble(campo.getText())));
    }

//    public static String formatarDecimal(double valor) {
//        NumberFormat formatter = new DecimalFormat("###0.00");
//        return (formatter.format(valor));
//    }
    public static JFormattedTextField getTelefone() {
        return getFormatado("(##) #####-####");
    }

    public static JFormattedTextField getCep() {
        return getFormatado("#####-###");
    }

    public static JFormattedTextField getCelular() {
        return getFormatado("(##) ######-####");
    }

    public static JFormattedTextField getCNPJ() {
        return getFormatado("##.###.###/####-##");
    }

    public static JFormattedTextField getCPF() {
        return getFormatado("###.###.###-##");
    }

    public static JFormattedTextField getData() {
        return getFormatado("##/##/####");
    }

    public static JFormattedTextField getDataHora() {
        return getFormatado("##/##/#### ##:##");
    }

    public void formatoDecimal(JTextField campo) {
        campo.setText(df.format(Double.parseDouble(campo.getText())));
    }

    public static void formatarReal(JFormattedTextField campo) {
        try {
            DecimalFormat decimal = new DecimalFormat("#,##0.00");
            NumberFormatter numFormatter = new NumberFormatter(decimal);
            numFormatter.setFormat(decimal);
            numFormatter.setAllowsInvalid(false);
            DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
            campo.setFormatterFactory(dfFactory);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void formatarData(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##/##/####");
            campo.setCaretPosition(0);
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void formatarCpf(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("###.###.###-##");
            campo.setCaretPosition(0);
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void formatarCep(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("#####-###");
            campo.setCaretPosition(0);
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void formatarCnpj(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##.###.###/####-##");
            campo.setCaretPosition(0);
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void formatarTelefone(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("(##)####-####");
            campo.setCaretPosition(0);
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void formatarCelular(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("(##)#####-####");
            campo.setCaretPosition(0);
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static String ajustaDataDMA(String data) {
        String dataFormatada = null;
        if (data == null) {
            dataFormatada = null;
        } else {
            try {
                Date dataAMD = new SimpleDateFormat("yyyy-MM-dd").parse(data);
                dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataAMD);

            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return (dataFormatada);
    }

    public static String ajustaDataAMD(String data) {
        String dataFormatada = null;
        if (data == null) {
            dataFormatada = null;
        } else {
            try {
                Date dataDMA = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(dataDMA);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return (dataFormatada);
    }

    public static String removerFormatacao(String dado) {
        String retorno = "";
        for (int i = 0; i < dado.length(); i++) {
            if (dado.charAt(i) != '.' && dado.charAt(i) != '/' && dado.charAt(i) != '-' && dado.charAt(i) != '(' && dado.charAt(i) != ')') {
                retorno = retorno + dado.charAt(i);
            }
        }
        return (retorno);
    }

    public static String getDataAtual() {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dataHoje = df.format(now);

        return dataHoje;
    }

    public static String getDataMes() {
        GregorianCalendar today = new GregorianCalendar();
        today.add(Calendar.DAY_OF_MONTH, -60); //retrocede 60 dias
        Date incioMes = today.getTime();
//        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dataHoje = df.format(incioMes);

        return dataHoje;
    }

    public static String getDataHoraAtual() {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataHoje = df.format(now);

        return dataHoje;
    }
}
