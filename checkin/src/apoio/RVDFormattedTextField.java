/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import javax.swing.JFormattedTextField;

/**
 *
 * @author mateus.roveda
 */
public class RVDFormattedTextField extends JFormattedTextField {

    public RVDFormattedTextField() {
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }

    public static void setBorda(RVDFormattedTextField campo) {
        campo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }

    public static boolean isEmpty(RVDFormattedTextField campo) {
        Boolean ok = true;
        if (Formatacao.removerFormatacao(campo.getText()).trim().isEmpty()) {
            ok = true;
            campo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));
        } else {
            ok = false;
            campo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
        return ok;
    }

    public static boolean isEmpty(RVDFormattedTextField[] campos) {
        Boolean ok = false;
        for (int i = 0; i < campos.length; i++) {
            if (Formatacao.removerFormatacao(campos[i].getText()).trim().isEmpty()) {
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
