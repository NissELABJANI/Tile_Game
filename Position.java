/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;

/**
 *
 * @author ELABJANI
 */
public class Position {
    private int x; // La coordonnée X sur le plateau
    private int y; // La coordonnée Y sur le plateau

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters et setters pour les coordonnées
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Méthode pour vérifier si deux positions sont égales
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    // Méthode pour générer le hashCode de l'objet Position
    @Override
    public int hashCode() {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        return result;
    }

    // Méthode pour représenter la Position sous forme de chaîne de caractères
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
