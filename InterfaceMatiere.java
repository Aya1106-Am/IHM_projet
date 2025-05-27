import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceMatiere extends JFrame {
    public InterfaceMatiere() {
        super("Interface Matiere");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        ImagePanel backgroundPanel = new ImagePanel("tab2.jpg");
        setContentPane(backgroundPanel);

        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.ipadx = 80;
        gbc.ipady = 40;

        gbc.insets = new Insets(20, 20, 0, 20);

        // Label en haut
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // occupe toute la largeur
        JLabel chooseLabel = new JLabel("Choisissez une matière", SwingConstants.CENTER);
        chooseLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Taille de la police
        chooseLabel.setForeground(Color.BLUE); // Couleur du texte
        chooseLabel.setBackground(Color.WHITE); // Couleur de fond
        chooseLabel.setOpaque(true); // Activer la couleur de fond
        backgroundPanel.add(chooseLabel, gbc);

        // Boutons à gauche
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // réinitialiser la largeur
        JButton geoButton = creerBouton("Géographie", "geo1.jpg");
        backgroundPanel.add(geoButton, gbc);

        geoButton.addActionListener(new ActionListener() {
            @Override
               public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    geo.creerEtAfficherUII();
                   });
               }
       });

        gbc.gridy = 2;
        JButton histButton = creerBouton("Histoire", "histoire.jpg");
        backgroundPanel.add(histButton, gbc);

        histButton.addActionListener(new ActionListener() {
            @Override
               public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                   HistoryGame.creerEtAfficherUI();
                   });
               }
       });

        gbc.gridy = 3;
        JButton frButton = creerBouton("Français", "francais.jpg");
        backgroundPanel.add(frButton, gbc);
       
        frButton.addActionListener(new ActionListener() {
            @Override
               public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                   InterfaceFrancais.creerEtAfficherUII();
                   });
               }
       });

        // Boutons à droite
        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton mathButton = creerBouton("Math", "math.jpg");
        backgroundPanel.add(mathButton, gbc);

        mathButton.addActionListener(new ActionListener() {
            @Override
               public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                   InterfaceEnfantMath.creerEtAfficherUII();
                   });
               }
       });

        gbc.gridy = 2;
        JButton sportButton = creerBouton("Sport", "sport.jpg");
        backgroundPanel.add(sportButton, gbc);

        sportButton.addActionListener(new ActionListener() {
            @Override
               public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                   interfaceEnfantSport.creerEtAfficherUII();
                   });
               }
       });

        gbc.gridy = 3;
        JButton engButton = creerBouton("Anglais", "anglais.jpg");
        backgroundPanel.add(engButton, gbc);
        
        engButton.addActionListener(new ActionListener() {
            @Override
               public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                   InterfaceEnfant.creerEtAfficherUII();
                   });
               }
       });

       

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton creerBouton(String texte, String imagePath) {
        JButton bouton = new JButton(texte);

        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        bouton.setIcon(new ImageIcon(image));

        bouton.setHorizontalTextPosition(JButton.CENTER);
        bouton.setVerticalTextPosition(JButton.BOTTOM);
        bouton.setFont(new Font("Arial", Font.BOLD, 10));
        bouton.setForeground(Color.BLUE);
        bouton.setBackground(Color.WHITE);

        return bouton;
    }

    private static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

   

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceMatiere());
    }
    
}