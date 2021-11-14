package apoio;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LimpaCampos {

    public static void limparCampos(Container container) {
        Component c[] = container.getComponents();
        for (int i = 0; i < c.length; i++) {
            if (c[i] instanceof JFormattedTextField) {
                JFormattedTextField field = (JFormattedTextField) c[i];
                field.setValue(null);
                field.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            } else if (c[i] instanceof JTextField) {
                JTextField field = (JTextField) c[i];
                field.setText("");
                field.setBorder(javax.swing.BorderFactory.createEtchedBorder());
//            } else if (c[i] instanceof JScrollPane) {
//                 ((JTextArea) ((JScrollPane) c[i]).getViewport().getComponent(0)).setText("");
            } else if (c[i] instanceof JComboBox) {
                JComboBox cb = (JComboBox) c[i];
                cb.setSelectedIndex(0);
            } else if (c[i] instanceof JCheckBox) {
                JCheckBox ckb = (JCheckBox) c[i];
                ckb.setSelected(false);
            } else if (c[i] instanceof RVDTextField) {
                RVDTextField field = (RVDTextField) c[i];
                field.setText("");
                field.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            } else if (c[i] instanceof RVDFormattedTextField) {
                RVDFormattedTextField field = (RVDFormattedTextField) c[i];
                field.setValue(null);
                field.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            } else if (c[i] instanceof JPasswordField) {
                JPasswordField field = (JPasswordField) c[i];
                field.setText(null);
                field.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            }
        }
    }
}
