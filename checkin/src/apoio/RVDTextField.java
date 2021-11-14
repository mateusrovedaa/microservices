/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author mateus.roveda
 */
public class RVDTextField extends JTextField {

    public RVDTextField() {
            setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }

    public static void setBorda(RVDTextField campo) {
        campo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }

    public boolean isEmpty(RVDTextField campo) {
        Boolean ok = true;
        if (campo.getText().trim().isEmpty()) {
            ok = true;
            campo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));
        } else {
            ok = false;
            campo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
        return ok;
    }

    public static boolean isEmpty(RVDTextField[] campos) {
        Boolean ok = false;
        for (int i = 0; i < campos.length; i++) {
            if (campos[i].getText().trim().isEmpty()) {
                ok = true;
                if (campos[i].isEditable()) {
                    campos[i].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));
                }
            } else {
                campos[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
            }
        }
        return ok;
    }
}
