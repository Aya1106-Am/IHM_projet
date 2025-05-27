import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class interfaceEnfantSport extends JFrame {
    public interfaceEnfantSport() {
        // Titre de la fenêtre
        super("Interface Enfant");

        // Paramètres de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Créer un panneau personnalisé avec une image de fond
        ImagePanel backgroundPanel = new ImagePanel("ecole.png");
        setContentPane(backgroundPanel);

        // Utiliser GridBagLayout pour plus de contrôle sur la position des composants
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 40;
        gbc.ipady = 40;

        // Label central "Choisissez le niveau"
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // occupe toute la largeur
        JLabel chooseLevelLabel = new JLabel("Choisissez le niveau");
        chooseLevelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        chooseLevelLabel.setForeground(Color.BLUE);
        chooseLevelLabel.setBackground(Color.WHITE);
        chooseLevelLabel.setOpaque(true);
        chooseLevelLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte
        backgroundPanel.add(chooseLevelLabel, gbc);

        // Espacement entre les boutons
        gbc.insets = new Insets(20, 40, 0, 40);

        // Ajouter des boutons avec des images pour les niveaux
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // réinitialiser la largeur
        JButton facileButton = creerBouton("Facile", "puzzle.jpg");
        backgroundPanel.add(facileButton, gbc);

        gbc.gridx = 1;
        JButton difficileButton = creerBouton("Difficile", "dif.png");
        backgroundPanel.add(difficileButton, gbc);

        // Ajouter des listeners pour les boutons
      facileButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    
                   SwingUtilities.invokeLater(() -> {
                    try {
                        QuizSportfacil.creerEtAfficherUI();
                    } catch (IOException e1) {
                        
                        e1.printStackTrace();
                    }
                    
                    });
                }
        });

        difficileButton.addActionListener(new ActionListener() {
            @Override
               public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                   QuizSportDef.creerEtAfficherUI();
                   });
               }
       });
       
        // Centrer la fenêtre
        setLocationRelativeTo(null);

        // Afficher la fenêtre
        setVisible(true);
    }

    private JButton creerBouton(String texte, String imagePath) {
        JButton bouton = new JButton(texte);

        // Ajouter une image au bouton
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        bouton.setIcon(new ImageIcon(image));

        // Personnaliser l'apparence du bouton
        bouton.setHorizontalTextPosition(JButton.CENTER);
        bouton.setVerticalTextPosition(JButton.BOTTOM);
        bouton.setFont(new Font("Arial", Font.BOLD, 16));
        bouton.setForeground(Color.BLUE);
        bouton.setBackground(Color.white);

        return bouton;
    }

    // Classe de panneau personnalisé avec une image de fond
    private static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dessiner l'image de fond
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    public static void main(String[] args) {
       
        creerEtAfficherUII();
    }
    static void creerEtAfficherUII()    {
         SwingUtilities.invokeLater(() -> new interfaceEnfantSport());
     }
}
