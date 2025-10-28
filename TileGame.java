/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class TileGame extends JFrame {
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;
    private Sac sac;
    private JPanel boardPanel, controlPanel, selectedTilePanel;
    private JLabel statusLabel;
    private JButton[][] tileButtons;
    Tuile tuileActuelle;
    private final int gridSize = 8; // Pour la simplicité, l'affichage d'une section 8x8
    private int joueurActuelIndex = 0;
    private final Sound soundPlayer;

    public TileGame(int par) {
        // Initialisez le lecteur de son
        soundPlayer = new Sound();
        soundPlayer.playMusic("C:\\Users\\ELABJANI\\Documents\\JavaProg2\\Jeu2\\src\\jeu2\\musique\\musique.wav"); // Mettez ici le chemin vers votre fichier de musique
        initGame();
        getContentPane().setBackground(Color.LIGHT_GRAY); // Change la couleur de fond de la fenêtre
        initUI();
        placeInitialTile();
        updateSelectedTilePanel(); //mise à jour initiale pour afficher la première tuile
    }
    
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (soundPlayer != null) {
                soundPlayer.stopMusic(); // Arrêtez la musique lorsque la fenêtre se ferme
            }
        }
    }

    private void initGame() {
        joueurs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            joueurs.add(new Joueur("Joueur " + (i + 1)));
        }
        sac = new Sac();
        plateau = new Plateau(gridSize, gridSize);
        tuileActuelle = sac.supprimer_tuile(); //Tuile initiale
    }

    private void initUI() {
        setTitle("Jeu de Tuiles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        boardPanel = new JPanel(new GridLayout(gridSize, gridSize));
        boardPanel.setBackground(Color.PINK); // Change la couleur de fond du plateau de jeu
        tileButtons = new JButton[gridSize][gridSize];
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                JButton button = new JButton();
                button.addActionListener(new TileActionListener(x, y));
                boardPanel.add(button);
                tileButtons[y][x] = button;
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.PINK); // Change la couleur de fond du panneau de contrôle
        JButton drawButton = new JButton("Piocher Tuile");
        drawButton.addActionListener(e -> drawTile());
        JButton rotateButton = new JButton("Tourner Tuile");
        rotateButton.addActionListener(e -> rotateTile());
        controlPanel.add(drawButton);
        controlPanel.add(rotateButton);
        add(controlPanel, BorderLayout.PAGE_END);

        statusLabel = new JLabel();
        add(statusLabel, BorderLayout.PAGE_START);

        selectedTilePanel = new JPanel();
        selectedTilePanel.setPreferredSize(new Dimension(120, 120));
        selectedTilePanel.setBorder(BorderFactory.createTitledBorder("Tuile Sélectionnée"));
        selectedTilePanel.setBackground(Color.PINK); // Change la couleur de fond du panneau de la tuile sélectionnée

        add(selectedTilePanel, BorderLayout.EAST);

        updateStatus();
        pack();
        setLocationRelativeTo(null); // Centre sur l'écran
        setVisible(true);
    }

    private void drawTile() {
        if (!sac.isEmpty()) {
            tuileActuelle = sac.supprimer_tuile();
            updateSelectedTilePanel();
            updateStatus();
        } else {
            JOptionPane.showMessageDialog(this, "Le sac est vide. Plus de tuiles à piocher.");
        }
    }

    private void rotateTile() {
        if (tuileActuelle != null) {
            tuileActuelle.retourner();
            updateSelectedTilePanel();
        }
    }

    void updateSelectedTilePanel() {
        selectedTilePanel.removeAll();
        if (tuileActuelle != null) {
            JLabel label = new JLabel(createTileText(tuileActuelle), SwingConstants.CENTER);
            selectedTilePanel.add(label);
        }
        selectedTilePanel.revalidate();
        selectedTilePanel.repaint();
    }

    private String createTileText(Tuile tuile) {
        StringBuilder sb = new StringBuilder("<html>");
        // Assuming the order of sides is top, right, bottom, and left (0, 1, 2, 3 respectively)
        for (int i = 0; i < 4; i++) {
            int[] side = tuile.getSide(i);
            sb.append(Arrays.toString(side)).append("<br>");
        }
        sb.append("</html>");
        return sb.toString();
    }

    private void placeInitialTile() {
        //la tuile initiale est placée dans le centre du plateau 
        plateau.ajouter_Tuile(tuileActuelle, gridSize / 2, gridSize / 2);
        updateBoard();
    }

    private void updateStatus() {
        Joueur currentPlayer = joueurs.get(joueurActuelIndex);
        statusLabel.setText("Current Player: " + currentPlayer.getNom() + ", Points: " + currentPlayer.getPoints());
    }

    private void updateBoard() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                Tuile tuile = plateau.getTuileAt(x, y);
                JButton button = tileButtons[y][x];
                if (tuile != null) {
                    button.setText(createTileText(tuile));
                    button.setEnabled(false); //Si la tuile est placée 
                } else {
                    button.setText(""); //Enable the button if no tile is placed
                }
            }
        }
    }

    private class TileActionListener implements ActionListener {
        private final int x;
        private final int y;

        public TileActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tuileActuelle != null && plateau.Trouver_correspondance(tuileActuelle) != null) {
                plateau.ajouter_Tuile(tuileActuelle, x, y);
                int points = plateau.calculerPoints(tuileActuelle, new Position(x, y));
                joueurs.get(joueurActuelIndex).addPoints(points);
                tuileActuelle = null;
            } else {
                JOptionPane.showMessageDialog(TileGame.this, "Pas de correspondance, tuile défaussée !");
            }
            joueurActuelIndex = (joueurActuelIndex + 1) % joueurs.size();
            updateBoard();
            updateStatus();
            drawTile(); //on dessine la tuile suivante pour le joueur suivant 
        }
    }

    public static void main(String[] args) {
        new TileGame(8);
    }
}
