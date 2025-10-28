/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;

/**
 *
 * @author ELABJANI
 */
public class Tuile {
    public int[][] sides;
    
    
    public Tuile(int[] haut,int[] droite,int[] bas,int[]gauche){
        this.sides=new int[][]{haut,droite,bas,gauche};
    }
    
    // Cette méthode retourne les valeurs pour un côté spécifié
    public int[] getSide(int sideIndex) {
        if (sideIndex < 0 || sideIndex >= sides.length) {
            throw new IllegalArgumentException("Index de côté invalide: " + sideIndex);
        }
        return sides[sideIndex];
    }
    
    public void retourner(){
        // Sauvegarde des côtés avant la rotation
        int[] haut = sides[0];
        int[] droite = sides[1];
        int[] bas = sides[2];
        int[] gauche = sides[3];

        // Réorganisation des côtés après la rotation
        sides[0] = gauche;  // haut devient gauche
        sides[1] = haut;    // droite devient haut
        sides[2] = droite;  // bas devient droite
        sides[3] = bas;     // gauche devient bas
    }
    
    // Méthode pour afficher la tuile 
    public void afficherTuile() {
        for (int[] side : sides) {
            for (int num : side) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Exemple d'utilisation
        int[] haut = {1, 2, 3};
        int[] droite = {4, 1, 2};
        int[] bas = {2, 1, 3};
        int[] gauche = {3,4,2};

        Tuile tuile = new Tuile(haut, droite, bas, gauche);
        System.out.println("Tuile avant rotation :");
        tuile.afficherTuile();

        tuile.retourner();
        System.out.println("\nTuile apres rotation :");
        tuile.afficherTuile();
    }
    
    
    
    
}
