/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;

/**
 *
 * @author ELABJANI
 */

import java.util.Arrays;

public class Plateau {
    private final Tuile[][] grille;  // Grille pour représenter le plateau de jeu
    private final int largeur;       // Nombre de colonnes dans la grille
    private final int hauteur;       // Nombre de rangées dans la grille

    public Plateau(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        grille = new Tuile[hauteur][largeur];
    }

    public void ajouter_Tuile(Tuile tuile, int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            grille[y][x] = tuile;
        } 
        else {
            throw new IllegalArgumentException("Position hors des limites");
        }
    }

    public Tuile getTuileAt(int x, int y) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            return grille[y][x];
        }
        return null;
    }

    public Tuile Trouver_correspondance(Tuile tuile) {
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Tuile existingTuile = grille[y][x];
                if (existingTuile != null && correspondance(existingTuile, tuile)) {
                    return existingTuile;
                }
            }
        }
        return null;
    }
    
    public boolean correspondance(Tuile existingTuile, Tuile tuile){
        return Arrays.equals(tuile.sides[0], existingTuile.sides[2]) ||
               Arrays.equals(tuile.sides[1], existingTuile.sides[3]) ||
               Arrays.equals(tuile.sides[2], existingTuile.sides[0]) ||
               Arrays.equals(tuile.sides[3], existingTuile.sides[1]);
    }
    
    public int calculerPoints(Tuile tuile, Position position) {
    int points = 0;
    // Pour chaque côté de la tuile,on vérifie si elle est adjacente à une autre tuile
    for (int i = 0; i < 4; i++) {
        Position posAdjacente = getPositionAdjacente(position, i);
        Tuile tuileAdjacente = getTuileAt(posAdjacente.getX(), posAdjacente.getY());
        
        if (tuileAdjacente != null) {
            //on récupère uniquement le côté de la tuile actuellement placée pour calculer les points
            int[] coteTuile = tuile.getSide(i);
            // Additionnez les valeurs des chiffres pour le côté en contact
            for (int j = 0; j < coteTuile.length; j++) {
                points += coteTuile[j];
            }
        }
    }
    
    return points;
}
    
    public Position getPositionAdjacente(Position position, int sideIndex) {
        switch (sideIndex) {
            case 0: // haut
                return new Position(position.getX(), position.getY() - 1);
            case 1: // droite
                return new Position(position.getX() + 1, position.getY());
            case 2: // bas
                return new Position(position.getX(), position.getY() + 1);
            case 3: // gauche
                return new Position(position.getX() - 1, position.getY());
            default:
                throw new IllegalArgumentException("Index de côté invalide: " + sideIndex);
        }
    }
    
    public void afficherPlateau() {
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (grille[y][x] != null) {
                    grille[y][x].afficherTuile();
                } else {
                    System.out.print("Vide ");
                }
            }
            System.out.println();
        }
    }
    
    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
}

    




