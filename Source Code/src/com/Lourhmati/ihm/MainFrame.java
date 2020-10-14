package com.Lourhmati.ihm;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Classe MainFrame extends JFrame dans lequel j'ajoute tout les élements du jeu
 * @author Oussama Lourhmati 1739188
 */
public class MainFrame extends JFrame {

    private JeuPanel jeuPanel;
    private ChronoPanel jeuTimer;

    public MainFrame() {
        super("Jeu de Mémoire");
        
        //Le timer
        jeuTimer = new ChronoPanel();
        add(jeuTimer, BorderLayout.SOUTH);
        
        //Mon panel
        jeuPanel = new JeuPanel(jeuTimer);
        add(jeuPanel, BorderLayout.CENTER);
        
        //Ajuster le mainFrame
        setSize(515, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
