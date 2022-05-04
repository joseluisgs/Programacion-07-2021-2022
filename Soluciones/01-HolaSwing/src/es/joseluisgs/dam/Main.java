package es.joseluisgs.dam;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hola Swing");
        JFrame frame = new JFrame("Dam");
        frame.setPreferredSize(new Dimension(640, 480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new EjemploSwing().rootPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Para centralo en la pantalla
        frame.setVisible(true);
    }
}
