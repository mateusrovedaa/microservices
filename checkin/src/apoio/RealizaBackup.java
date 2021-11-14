/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author movez
 */
public class RealizaBackup {

    public static void realizaBackup() throws IOException, InterruptedException {
        final List<String> comandos = new ArrayList<String>();

        //comandos.add("C:\\Program Files (x86)\\PostgreSQL\\8.4\\bin\\pg_dump.exe"); 
        //comandos.add("C:\\Program Files\\PostgresPlus\\8.4SS\\bin\\pg_dump.exe"); 
        comandos.add("C:\\Arquivos de programas\\PostgreSQL\\10\\bin\\pg_dump.exe");    // esse é meu caminho  

//        comandos.add("-i");
        comandos.add("-h");
        comandos.add("localhost");     //ou  comandos.add("192.168.0.1"); 
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add("postgres");
        comandos.add("-F");
        comandos.add("c");
        comandos.add("-b");
        comandos.add("-v");
        comandos.add("-f");

        comandos.add("C:\\Users\\movez\\bkp.backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
        comandos.add("postgres");
        ProcessBuilder pb = new ProcessBuilder(comandos);

        pb.environment().put("PGPASSWORD", "postgres");      //Somente coloque sua senha         

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
            JOptionPane.showMessageDialog(null, "backup realizado com sucesso.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

}
