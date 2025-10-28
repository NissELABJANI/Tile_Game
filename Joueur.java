/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;

/**
 *
 * @author ELABJANI
 */
public class Joueur {
  
    private final String nom;
    private int points;

    public Joueur(String nom) {
        this.nom = nom;
        this.points = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public String toString() {
        return nom + " a " + points + " points.";
    }
    
   
}

