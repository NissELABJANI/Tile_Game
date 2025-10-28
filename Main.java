/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;

import java.util.Scanner;

/**
 *
 * @author ELABJANI
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nombre de joueurs : ");
        int nombreDeJoueurs = scanner.nextInt();
        
        Jeu jeu = new Jeu(nombreDeJoueurs);
        jeu.jouer();
        
        scanner.close();
    }
}
