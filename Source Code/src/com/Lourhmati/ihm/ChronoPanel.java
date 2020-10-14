package com.Lourhmati.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe ChronoPanel qui contient le JLabel pour afficher le chronometre
 * @author Oussama Lourhmati 1739188
 */
public class ChronoPanel extends JPanel {
    public JLabel label;
    /**
     * Constructeur qui instancie le label initial et les couleurs
     * @param aucun
     */
    public ChronoPanel() {
        label = new JLabel("Temps restant: ");
        this.setBackground(new java.awt.Color(255, 102, 0));
        label.setForeground(Color.white);
        add(label, BorderLayout.NORTH);//Ajouter le label dans le ChronoPanel

    }
}
