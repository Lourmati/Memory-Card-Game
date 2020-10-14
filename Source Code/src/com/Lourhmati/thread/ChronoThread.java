package com.Lourhmati.thread;

import com.Lourhmati.ihm.ChronoPanel;
import com.Lourhmati.ihm.JeuPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe ChronoThread extends Thread dans lequel je crée le chronometre et les
 * traitements selon le chronometre.
 *
 * @author Oussama Lourhmati 1739188
 */
public class ChronoThread extends Thread {

    //Creer les variables
    private JeuPanel jeuPanel;
    private ChronoPanel jeuTimer;
    private int intervale;
    private final Timer timer;
    private JLabel termine;
    private int compteur = 0;

    /*
    * Constructeur ChronoThread dans lequel je crée le thread du chronometre et
    * et les traitements si l'utilisateur perd ou gagne la partie.
    *
    * @param jeuPanel, jeuTimer
     */
    public ChronoThread(final ChronoPanel jeuTimer, final JeuPanel jeuPanel) {
        this.jeuTimer = jeuTimer;
        this.jeuPanel = jeuPanel;

        //Creation du chronometre
        intervale = 85;
        int delai = 1000;
        int periode = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                jeuTimer.label.setText("Temps restant: " + getIntervale());

                //Si le chronometre se rend a 0, j'efface tout et j'affiche 
                //l'image. L'utilisateur a perdu.
                if (intervale == 0) {
                    //Enlever tout des panels
                    jeuPanel.removeAll();
                    jeuPanel.setBackground(new java.awt.Color(0, 51, 153));
                    jeuTimer.removeAll();
                    jeuTimer.setBackground(new java.awt.Color(0, 51, 153));

                    //Afficher le aide.png
                    ImageIcon image = new ImageIcon("aide.png");
                    JLabel label = new JLabel(image);
                    label.setBounds(0, 0, 415, 475);
                    jeuPanel.add(label, BorderLayout.CENTER);
                    jeuPanel.updateUI();
                    jeuTimer.updateUI();
                }

            }
        }, delai, periode);
    }

    /*
    * Methode pour recuperer la variable intervale pour chaque seconde passé
    * @param aucun
     */
    private final int getIntervale() {
        if (intervale == 1) {
            timer.cancel();
        }
        return --intervale;
    }
}
