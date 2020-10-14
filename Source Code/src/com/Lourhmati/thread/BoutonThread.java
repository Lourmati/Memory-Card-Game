package com.Lourhmati.thread;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import com.Lourhmati.ihm.ChronoPanel;
import com.Lourhmati.ihm.JeuPanel;

/**
 * Classe BoutonThread pour faire le traitement lorsque deux boutons n'ont pas
 * la meme image.
 *
 * @author Oussama Lourhmati 1739188
 */
public class BoutonThread extends Thread {

    private int compteur = 0;
    private ChronoPanel jeuTimer;
    private JeuPanel jeuPanel;

    /*
    * Constructeur BoutonThread
    * @param jeuPanel
     */
    public BoutonThread(JeuPanel jeuPanel, ChronoPanel jeuTimer) {
        this.jeuPanel = jeuPanel;
        this.jeuTimer = jeuTimer;
    }

    @Override
    public void run() {
        try {

            //Désactiver les boutons avant le sleep
            for (JButton currentButton : jeuPanel.boutons) {
                for (ActionListener e : currentButton.getActionListeners()) {
                    currentButton.removeActionListener(e);
                }
            }

            sleep(1000);//Laisser les images 1 secondes avant de les retourner
        } catch (InterruptedException ex) {
            Logger.getLogger(BoutonThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Remettre les boutons a l'icone vide
        jeuPanel.boutons[jeuPanel.cliqueCourant].setIcon(jeuPanel.iconVide);
        jeuPanel.boutons[jeuPanel.premierClique].setIcon(jeuPanel.iconVide);

        //Activer les ActionListener pour mes boutons
        for (int i = 0; i < jeuPanel.boutons.length; i++) {
            jeuPanel.boutons[i].addActionListener(jeuPanel);
        }
    }
}
