/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkin;

import apoio.ConexaoBD;
import checkin.controller.Gateway;
import checkin.view.FrmLogin;
import java.io.IOException;

/**
 *
 * @author mateus
 */
public class Checkin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ConexaoBD.getInstance().getConnection();
        Gateway gateway = new Gateway();
        FrmLogin login = new FrmLogin(gateway);
        login.setVisible(true);
    }

}
