package es.joseluisgs.dam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EjemploSwing {
    private JTextArea txtNombre;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    JPanel rootPanel;

    public EjemploSwing() {
        // Iniciamos las cosas...
        txtNombre.setText("");
        txtNombre.requestFocus();

        // Programamos los eventos, con Listeners y ActionListeners
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hola " + txtNombre.getText());
            }
        });

        // O usando lambdas
        btnLimpiar.addActionListener(e -> txtNombre.setText(""));
    }

}
