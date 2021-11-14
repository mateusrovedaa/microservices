/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkin;

import checkin.controller.Gateway;
import apoio.HttpUtil;
import checkin.view.FrmLogin;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.JComboBox;

/**
 *
 * @author mateus
 */
public class Checkin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Gateway gateway = new Gateway();
        FrmLogin login = new FrmLogin(gateway);
        login.setVisible(true);
    }

}
