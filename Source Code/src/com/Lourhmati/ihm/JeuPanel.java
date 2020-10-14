package com.Lourhmati.ihm;

import com.Lourhmati.thread.ChronoThread;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import com.Lourhmati.thread.BoutonThread;

/**
 * Classe JeuPanel extends JPanel dans lequel je crée les boutons,je les mélange
 * et je les traite avec ActionListener.
 *
 * @author Oussama Lourhmati 1739188
 */
public class JeuPanel extends JPanel implements ActionListener {
    
    private ChronoPanel jeuTimer;
    private ChronoThread timerThread;
    private BoutonThread monThread;
    public JButton[] boutons;
    private String photos[] = {"ajax.png", "atletico.png", "bayern.png", "bvb.png",
        "juventus.png", "arsenal.png", "psg.png", "milan.png"};
    public ImageIcon iconVide = new ImageIcon("ucl.png");
    protected ImageIcon icones[];
    private int cliques = 0, compteur = 0;
    public int premierClique = 0, cliqueCourant = 0, nbBoutons = 0;
    private GridLayout layout = new GridLayout(4, 4, 2, 2);

    /**
     * Constructeur qui contient la création des boutons du jeu, création du
     * GridLayout et du ActionListener pour chaque boutton.
     *
     * @param jeuTimer
     */
    public JeuPanel(ChronoPanel jeuTimer) {
        //Démarrer le thread pour le chronomètre
        this.jeuTimer = jeuTimer;
        timerThread = new ChronoThread(jeuTimer, this);
        timerThread.start();

        //Ajuster les variables et le panel
        nbBoutons = photos.length * 2;//16 boutons
        icones = new ImageIcon[nbBoutons];//Tableau d'icones de 16
        boutons = new JButton[nbBoutons];//Tableau de JButton de 16
        setLayout(layout);//Ajuster le layout
        this.setBackground(new java.awt.Color(255, 102, 0));//Ajuster la couleur

        //Creer les boutons vide, ajuster les couleurs et ajouter ActionListener
        for (int i = 0; i < nbBoutons; i++) {
            boutons[i] = new JButton();
            boutons[i].setIcon(iconVide);
            boutons[i].setBackground(new java.awt.Color(255, 148, 77));
            boutons[i].setBorder(new LineBorder(new java.awt.Color(255, 102, 0)));
            boutons[i].addActionListener(this);
            add(boutons[i]);//Ajouter le bouton
        }

        //Initialiser icones[] à partir de de mon tableau de photos
        int j = 0;
        for (int i = 0; i < nbBoutons; i++) {
            icones[i] = new ImageIcon(photos[j]);
            j++;
            if (j == 8) {//Lorsqu'on se rend à 8, on recommence le compteur
                j = 0;
            }
        }
        shuffle(icones);//Mélanger le tableau d'icones
    }

    /**
     * Methode pour melanger mes icones (boutons)
     *
     * @param icones[]
     */
    public void shuffle(ImageIcon icones[]) {
        Random r = ThreadLocalRandom.current();
        for (int i = icones.length - 1; i > 0; i--) {
            int index = r.nextInt(i + 1);
            //Swap avec le temp
            ImageIcon temp = icones[index];
            icones[index] = icones[i];
            icones[i] = temp;
        }
    }

    /**
     * ActionListener pour chacun des boutons
     *
     * @param ActionEvent e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Verifier si la personne a terminé le jeu. La personne a gagné.
        compteur = 0;
        for (int i = 0; i < boutons.length; i++) {
            if (boutons[i].getIcon() == icones[i]) {//Verifier les icones
                compteur++;
            }
        }
        
        if (compteur == 15) {//On s'arrete à 15, car le dernier clique compte pas
            //Enlever tout des panels
            this.removeAll();
            this.setBackground(new java.awt.Color(51, 204, 0));
            this.updateUI();
            jeuTimer.removeAll();
            jeuTimer.setBackground(new java.awt.Color(51, 204, 0));
            jeuTimer.updateUI();

            //Afficher le bravo.png
            JLabel label = new JLabel("BRAVO VOUS AVEZ GAGNE!!!!!!");
            label.setForeground(Color.white);
            label.setFont(new Font("Impact", Font.BOLD, 40));
            add(label, BorderLayout.CENTER);
            
        }

        //La variable cliques correspond au nombre de cliques
        //Pour avoir un match, il faut deux cliques (avec meme image)
        //Dans les 2 cliques, il ya donc un premierClique
        cliques++;//Instancier cliques

        // Verifier le bouton qui a été cliqué
        for (int i = 0; i < nbBoutons; i++) {
            if (e.getSource() == boutons[i]) {
                boutons[i].setIcon(icones[i]);//Si il est égal, je change l'icone
                cliqueCourant = i;
            }
        }

        // Si le nombre de cliques est a 2, donc si c'est la deuxieme fois
        // que l'on clique.
        if (cliques == 2) {

            // Verifier si le meme bouton est cliqué 2 fois
            if (cliqueCourant == premierClique) {
                cliques--;//Enlever 1
                return;
            }
            // Comparer les deux images cliqué pour voir s'il y a un match
            if (icones[cliqueCourant].getImage() != icones[premierClique].getImage()) {
                monThread = new BoutonThread(this, jeuTimer);
                monThread.start();//Demarrer le thread
            } else {//S'il y a un match, enlever desactiver les boutons
                boutons[cliqueCourant].setEnabled(false);
                boutons[cliqueCourant].setDisabledIcon(icones[cliqueCourant]);
                boutons[premierClique].setEnabled(false);
                boutons[premierClique].setDisabledIcon(icones[premierClique]);
            }
            cliques = 0;//Remettre le nombre de cliques a 0 puisqu'on est a 2

        } else {
            // Si le nombre de cliques n'est pas a deux, c'est le premier clique
            premierClique = cliqueCourant;
        }
    }
}
