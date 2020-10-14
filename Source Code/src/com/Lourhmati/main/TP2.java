package com.Lourhmati.main;

import javax.swing.SwingUtilities;
import com.Lourhmati.ihm.MainFrame;

/**
 * Classe du main
 * @author Oussama Lourhmati 1739188
 */
public class TP2 {

    /**
     * Le main dans lequel je démarre le mainframe
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
            }
        });
    }
    
}
