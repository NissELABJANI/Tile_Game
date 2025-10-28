/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;


/**
 *
 * @author ELABJANI
 */

public class Jeu {
    private final Sac sac;
    private final Plateau plateau;
    private final Joueur[] joueurs;
    private int joueurActuelIndex;

    public Jeu(int nombreDeJoueurs) {
        sac = new Sac();
        plateau = new Plateau(100, 100);
        joueurs = new Joueur[nombreDeJoueurs];
        for (int i = 0; i < nombreDeJoueurs; i++) {
            joueurs[i] = new Joueur("Joueur " + (i + 1));
        }
        joueurActuelIndex = 0;
        Tuile tuileInitiale = sac.supprimer_tuile();
        plateau.ajouter_Tuile(tuileInitiale, 50, 50); // Position centrale, ajustée selon la logique du plateau
    }

    public void jouer() {
        while (!sac.isEmpty()) {
            Joueur joueurActuel = joueurs[joueurActuelIndex];
            System.out.println("Au tour du " + joueurActuel.getNom() + ", choisissez une tuile.");
            Tuile tuile = sac.supprimer_tuile();
            tuile.afficherTuile();
        
            if (plateau.Trouver_correspondance(tuile) != null) {
                plateau.ajouter_Tuile(tuile, 5, 5);  // La position centrale
                int points = plateau.calculerPoints(tuile, new Position(5, 5));  // Calcul des points pour ce placement
                joueurActuel.addPoints(points);
                System.out.println(joueurActuel.getNom() + " a placé une tuile et marque " + points + " points.");
            } else {
                System.out.println(joueurActuel.getNom() + " n'a pas trouvé de correspondance et passe son tour.");
            }

            joueurActuelIndex = (joueurActuelIndex + 1) % joueurs.length;
        }

        afficherScores();
    }
    
    private void afficherScores() {
        System.out.println("\nScores finaux:");
        Joueur vainqueur = joueurs[0];
        for (Joueur joueur : joueurs) {
            System.out.println(joueur);
            if (joueur.getPoints() > vainqueur.getPoints()) {
                vainqueur = joueur;
            }
        }
        System.out.println("Le vainqueur est " + vainqueur.getNom() + " avec " + vainqueur.getPoints() + " points.");
    }
    
    public Sac getSac() {
        return sac;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public int getJoueurActuelIndex() {
        return joueurActuelIndex;
    }
    
    // Méthode por mettre à jour l'index du joueur courant
    public void setJoueurActuelIndex(int index) {
        if (index >= 0 && index < joueurs.length) {
            this.joueurActuelIndex = index;
        } else {
            throw new IllegalArgumentException("Invalid player index");
        }
    }
}




        