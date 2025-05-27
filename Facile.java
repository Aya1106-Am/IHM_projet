import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Facile {

    private static Map<String, String[]> images = new HashMap<>();
    private static Map<String, String> phrases = new HashMap<>();
    private static JFrame frame;
    private static JLabel phraseLabel;
    private static JButton[] optionsButtons;
    private static JLabel resultatLabel;
    private static JLabel scoreLabel; // Nouvelle étiquette pour afficher le score
    private static int score;
    private static String cheminImageCorrecte;

    static {
        images.put("chévre", new String[]{"chevre1.jpg", "chevre2.jpg"});
        images.put("vache", new String[]{"vache1.jpg", "vache2.jpg"});
        images.put("robot", new String[]{"robot1.jpg", "robot2.jpg"});
       images.put("ordinateur", new String[]{"pc1.jpg", "pc2.jpg"});
       images.put("océon", new String[]{"oceon1.jpg", "oceon2.jpg"});
       images.put("foret", new String[]{"foret1.jpg", "foret2.jpg"});
       images.put("robot", new String[]{"robot1.jpg", "robot2.jpg"});
        images.put("pomme", new String[]{"PM1.jpg", "PM4.jpg"});
        images.put("faucon", new String[]{"faucon1.jpg", "faucon2.jpg"});
        images.put("rat", new String[]{"rat1.jpg", "rat2.jpg"});
        images.put("marmotte", new String[]{"marmotte1.jpg", "marmotte2.jpg"});
        images.put("hibou", new String[]{"HB1.jpg", "HB2.jpg"});
        images.put("chaise", new String[]{"chaise.jpg", "chaise2.jpg"});
       
        // Ajoutez plus d'images ici

       
        phrases.put("chévre", "Quelle image représente la chévre ?");
        phrases.put("vache", "Quelle image représente la vache ?");
        phrases.put("robot", "Quelle image représente le robot ?");
      phrases.put("ordinateur", "Quelle image représente l'ordinateur ?");
      phrases.put("océon", "Quelle image représente l'océon ?");
       phrases.put("foret", "Quelle image représente la foret ?");
         phrases.put("pomme", "Quelle image représente la pomme ?");
        phrases.put("faucon", "Quelle image représente faucon ?");
        phrases.put("rat", "Quelle image représente le rat ?");
        phrases.put("marmotte", "Quelle image représentele marmotte ?");
        phrases.put("hibou", "Quelle image représente le hibou ?");
        phrases.put("chaise", "Quelle image représente la chaise ?");
        // Ajoutez plus de phrases ici
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            creerEtAfficherUI();
        });
    }

    static void creerEtAfficherUI() {
        frame = new JFrame("Jeu d'Images");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
              try {
            // Charger l'image depuis le fichier
            BufferedImage originalImage = ImageIO.read(new File("fb2.jpg"));

            // Définir la nouvelle taille souhaitée (par exemple, 300x200 pixels)
            int newWidth = 600;
            int newHeight = 400;

            // Redimensionner l'image
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Créer une nouvelle ImageIcon avec l'image redimensionnée
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            // Créer un JLabel avec l'image redimensionnée comme contenu
            JLabel backgroundLabel = new JLabel(resizedIcon);

            // Définir le layout et le contenu du frame
            frame.setContentPane(backgroundLabel);
            frame.setLayout(new BorderLayout());

            // Afficher le frame
            frame.setSize(newWidth, newHeight);
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
 
        
        JPanel educatifPanel = new JPanel();
        educatifPanel.setOpaque(false);
        frame.add(educatifPanel, BorderLayout.CENTER);

        phraseLabel = new JLabel("",JLabel.CENTER);
        phraseLabel.setForeground(Color.BLACK);
        phraseLabel.setFont(new Font("Lato", Font.BOLD, 20));
    
        optionsButtons = new JButton[2];
        resultatLabel = new JLabel("", JLabel.CENTER);
        scoreLabel = new JLabel("Score: " + score, JLabel.RIGHT); // Initialiser le scoreLabel
      
        JPanel optionsPanel = new JPanel(new GridLayout(1, 2));
        optionsPanel.setOpaque(false);

        for (int i = 0; i < optionsButtons.length; i++) {
            optionsButtons[i] = new JButton();
            optionsButtons[i].setBackground(Color.PINK);
            optionsButtons[i].setForeground(Color.WHITE);
            optionsButtons[i].setOpaque(false);

            // Réduction de la taille des boutons
            Dimension buttonSize = new Dimension(15, 15);
            optionsButtons[i].setPreferredSize(buttonSize);
            optionsButtons[i].setBorderPainted(false);
            optionsButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    verifierReponse(((JButton) e.getSource()).getActionCommand());
                }
            });
            optionsPanel.add(optionsButtons[i]);
        }

        educatifPanel.setLayout(new BorderLayout());
        educatifPanel.add(phraseLabel, BorderLayout.NORTH);
        educatifPanel.add(optionsPanel, BorderLayout.CENTER);
        educatifPanel.add(resultatLabel, BorderLayout.SOUTH);

        // Ajouter scoreLabel en haut à droite
        educatifPanel.add(scoreLabel, BorderLayout.EAST);

        afficherNouvelleImage();

        frame.setVisible(true);
    }


    private static void afficherNouvelleImage() {
        Object[] imagesArray = images.keySet().toArray();
        String imageKey = imagesArray[new Random().nextInt(imagesArray.length)].toString();
        String[] imagePaths = images.get(imageKey);

        phraseLabel.setText(phrases.get(imageKey));
        optionsButtons = melangerBoutons(optionsButtons);

        // Assurer qu'une seule proposition est la bonne
        int indexCorrect = new Random().nextInt(optionsButtons.length);
        cheminImageCorrecte = imagePaths[0]; // Stocker le chemin de l'image correcte
        for (int i = 0; i < optionsButtons.length; i++) {
            if (i == indexCorrect) {
                optionsButtons[i].setActionCommand(cheminImageCorrecte);
            } else {
                // Pour l'autre option, choisissez une image incorrecte
                String imageIncorrecte = obtenirImageIncorrecte(imageKey);
                optionsButtons[i].setActionCommand(imageIncorrecte);
            }
            optionsButtons[i].setIcon(new ImageIcon(optionsButtons[i].getActionCommand()));
        }

        resultatLabel.setText("");
    }

    private static JButton[] melangerBoutons(JButton[] boutons) {
        Random random = new Random();
        for (int i = boutons.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Échanger les positions
            JButton temp = boutons[i];
            boutons[i] = boutons[index];
            boutons[index] = temp;
        }
        return boutons;
    }

    private static String obtenirImageIncorrecte(String imageCorrecte) {
        
        Object[] imagesArray = images.keySet().toArray();
        String imageIncorrecte;
        do {
            imageIncorrecte = imagesArray[new Random().nextInt(imagesArray.length)].toString();
        } while (imageCorrecte.equals(imageIncorrecte));
        return images.get(imageIncorrecte)[0];
    
    }

    private static void verifierReponse(String reponseUtilisateur) {
        if (cheminImageCorrecte != null) {
            if (cheminImageCorrecte.equals(reponseUtilisateur)) {
                resultatLabel.setForeground(new Color(0, 128, 0)); // Vert foncé
                resultatLabel.setText("Bravo! c'est correct! +1 point");
                score++;
                // Mettre à jour le texte du scoreLabel
                scoreLabel.setText("Score: " + score);


            } else {
                resultatLabel.setForeground(Color.RED);
                resultatLabel.setText("C'est incorrect 0 points");
            }
        }

        // Démarrer un timer pour changer automatiquement d'image après 2 secondes
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherNouvelleImage();
            }
        });
        timer.setRepeats(false); // N'exécutez le timer qu'une seule fois
        timer.start();
    }
}
