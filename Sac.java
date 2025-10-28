/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;

/**
 *
 * @author ELABJANI
 */
import java.util.ArrayList;
import java.util.Random;

public class Sac {
    ArrayList <Tuile> tuiles;
    
    public Sac(){
        this.tuiles = new ArrayList<>();
        // Ajout de 64 tuiles au sac
        for (int i = 0; i < 64; i++) {
            Tuile tuile = createRandomTuile();
            tuiles.add(tuile);
        }
    }
    
    private Tuile createRandomTuile() {
        Random random = new Random();
        int[] haut = generateRandomSide(random);
        int[] droite = generateRandomSide(random);
        int[] bas = generateRandomSide(random);
        int[] gauche = generateRandomSide(random);

        return new Tuile(haut, droite, bas, gauche);
    }
    
    private int[] generateRandomSide(Random random) {
        int[] side = new int[3];
        for (int j = 0; j < 3; j++) {
            side[j] = random.nextInt(2)+0; // Valeurs entre 0 et 1 inclus
        }
        return side;
    }
    
    public Tuile supprimer_tuile() {
        Random random = new Random();
        int index = random.nextInt(tuiles.size());
        return tuiles.remove(index);
    }
    
    public boolean isEmpty() {
        return tuiles.isEmpty();
    }
    
    public void afficherSac() {
        for (Tuile tuile : tuiles) {
            tuile.afficherTuile();
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Sac sac = new Sac();
        sac.afficherSac();
    }
}
