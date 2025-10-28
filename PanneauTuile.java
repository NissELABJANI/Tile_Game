/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ELABJANI
 */
public class PanneauTuile extends JPanel{
    private Tuile tuile;

    public PanneauTuile(Tuile tuile) {
        this.tuile = tuile;
        this.setPreferredSize(new Dimension(80, 80)); // Taille appropriée pour notre tuile
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Bordure pour visualiser le panel
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessinerTuile(g);
    }

    private void dessinerTuile(Graphics g) {
        // Dimensions pour dessiner les valeurs de la tuile
        int tileWidth = getLargeur();
        int tileHeight = getHauteur();
        int numWidth = tileWidth / 4; // Largeur pour chaque numéro
        int numHeight = tileHeight / 4; // Hauteur pour chaque numéro

        // Définir la police
        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        // Calculer les emplacements centraux pour le texte
        int centerX = tileWidth / 2 - numWidth / 2;
        int centerY = tileHeight / 2 - numHeight / 2;

        // Dessiner les valeurs de chaque côté de la tuile
        g.drawString(String.valueOf(tuile.getSide(0)[1]), centerX, numHeight); // Haut
        g.drawString(String.valueOf(tuile.getSide(1)[1]), tileWidth - numWidth, centerY); // Droite
        g.drawString(String.valueOf(tuile.getSide(2)[1]), centerX, tileHeight - numHeight / 4); // Bas
        g.drawString(String.valueOf(tuile.getSide(3)[1]), numWidth / 4, centerY); // Gauche

        // Dessiner les valeurs d'angle si nécessaire
        g.drawString(String.valueOf(tuile.getSide(0)[0]), numWidth / 4, numHeight); // Haut gauche
        g.drawString(String.valueOf(tuile.getSide(0)[2]), tileWidth - numWidth, numHeight); // Haut droite
        g.drawString(String.valueOf(tuile.getSide(2)[0]), numWidth / 4, tileHeight - numHeight / 4); // Bas gauche
        g.drawString(String.valueOf(tuile.getSide(2)[2]), tileWidth - numWidth, tileHeight - numHeight / 4); // Bas droite
    }

    private int getLargeur() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private int getHauteur() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            TileGame game = new TileGame(8); // Initiez avec un plateau de 8x8
            game.setVisible(true);
            
            // Créer une tuile pour tester l'affichage
            int[] haut = {1, 2, 3};
            int[] droite = {4, 1, 2};
            int[] bas = {2, 1, 3};
            int[] gauche = {3, 4, 2};
            Tuile tuile = new Tuile(haut, droite, bas, gauche);
            game.tuileActuelle = tuile; // Définir comme tuile actuelle
            game.updateSelectedTilePanel(); // Mettre à jour l'affichage
        }
    });
}

}
