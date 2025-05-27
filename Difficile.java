import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Difficile {

    private static Map<String, String[]> verbes = new HashMap<>();
    private static JFrame frame;
    private static JLabel verbeLabel;
    private static JButton[] optionsButtons;
    private static JLabel resultatLabel;
    private static int score;
    private static JLabel titreLabel; 

    static {
        verbes.put("manger", new String[]{"je manges", "tu  manges", "il/elle/on mange", "nous mangeons", "vous mangez", "ils/elles mangent"});
        verbes.put("lire", new String[]{"je lis", "tu lis", "nous lisons", "il/elle/on lit", "vous lisez", "ils/elles lisent"});
      verbes.put("marcher", new String[]{"je marches", "tu marches", "nous marchons", "il/elle/on marche", "vous marchez", "ils/elles marchent"});
      verbes.put("chanter", new String[]{"je chantes", "tu chantes", "nous chantons", "il/elle/on chante", "vous chantez", "ils/elles chantent"});
      verbes.put("dancer", new String[]{"je dances", "tu dances", "nous dancons", "il/elle/on dance", "vous dancez", "ils/elles dancent"});
      verbes.put("parler", new String[]{"je parles", "tu parles", "nous parlons", "il/elle/on parle", "vous parlez", "ils/elles parlent"});
      verbes.put("brosser", new String[]{"je brosses", "tu brosses", "nous brossons", "il/elle/on brosse", "vous brossez", "ils/elles brossent"});
      verbes.put("coiffer", new String[]{"je coiffes", "tu coiffes", "nous coiffons", "il/elle/on coiffe", "vous coiffez", "ils/elles coiffent"});
      verbes.put("jouer", new String[]{"je joues", "tu joues", "nous joueons", "il/elle/on joue", "vous jouez", "ils/elles jouent"});
        verbes.put("aller", new String[]{"je vais", "tu vas", "nous allons", "il/elle/on va", "vous allez", "ils/elles vont"});
        verbes.put("vouloir", new String[]{"je veux", "tu veux", "nous voulons", "il/elle/on veut", "vous voulez", "ils/elles veulent"});
        verbes.put("pouvoir", new String[]{"je peux", "tu peux", "nous pouvons", "il/elle/on peut", "vous pouvez", "ils/elles peuvent"});
       // Ajoutez plus de verbes ici
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            creerEtAfficherUI();
        });
    }


    
    static void creerEtAfficherUI() {
        frame = new JFrame("Conjugaison et Vocabulaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocation(250,50);
        
          try {
            BufferedImage originalImage = ImageIO.read(new File("fb.jpg"));
            int newWidth = 600;
            int newHeight = 400;
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel backgroundLabel = new JLabel(resizedIcon);
            frame.setContentPane(backgroundLabel);
            frame.setLayout(new BorderLayout());
            frame.setSize(newWidth, newHeight);
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel educatifPanel = new JPanel();
        educatifPanel.setOpaque(false);
        frame.add(educatifPanel, BorderLayout.CENTER);

        verbeLabel = new JLabel("",JLabel.CENTER);
        verbeLabel.setForeground(Color.BLACK);
        verbeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    
        optionsButtons = new JButton[3];
        resultatLabel = new JLabel("", JLabel.CENTER);

        JPanel optionsPanel = new JPanel(new GridBagLayout());
    optionsPanel.setOpaque(false); // Make sure the panel is transparent

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 20, 10, -100); // Espacement entre les boutons

    for (int i = 0; i < optionsButtons.length; i++) {
        optionsButtons[i] = new JButton();
        optionsButtons[i].setBackground(Color.PINK);
        optionsButtons[i].setForeground(Color.BLACK);
        optionsButtons[i].setOpaque(false);

        // Réduction de la taille des boutons
        Dimension buttonSize = new Dimension(140, 100);
        optionsButtons[i].setPreferredSize(buttonSize);
        optionsButtons[i].setBorderPainted(false);

        optionsButtons[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierReponse(((JButton) e.getSource()).getText());
            }
        });

        gbc.gridx = i % 3; // Colonne
        gbc.gridy = i / 1; // Ligne
        optionsPanel.add(optionsButtons[i], gbc);
    }

        for (int i = 0; i < optionsButtons.length; i++) {
            optionsButtons[i] = new JButton();
             optionsButtons[i].setForeground(Color.BLACK); 
            optionsButtons[i].setOpaque(false);
           
            optionsButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    verifierReponse(((JButton) e.getSource()).getText());
                }
            });
            optionsPanel.add(optionsButtons[i]);
        }

        educatifPanel.setLayout(new BorderLayout());
        educatifPanel.add(verbeLabel, BorderLayout.NORTH);
        educatifPanel.add(optionsPanel, BorderLayout.CENTER);
        educatifPanel.add(resultatLabel, BorderLayout.SOUTH);

        afficherNouveauVerbe();

        frame.setVisible(true);
    }

    private static void afficherNouveauVerbe() {
        Object[] verbesArray = verbes.keySet().toArray();
        String verbeInfinitif = verbesArray[new Random().nextInt(verbesArray.length)].toString();
        String[] conjugaisons = verbes.get(verbeInfinitif);
    
        verbeLabel.setText("Quelle est la conjugaison correcte pour le verbe  : " + verbeInfinitif);
    
        // Mélanger les conjugaisons correctes pour afficher dans un ordre aléatoire
        String[] conjugaisonsMelangees = melangerConjugaisons(conjugaisons);
    
        // Ajouter des conjugaisons incorrectes
        String[] conjugaisonsIncorrectes = genererConjugaisonsIncorrectes(conjugaisons);
    
        // Mélanger toutes les conjugaisons (correctes et incorrectes)
        String[] toutesConjugaisons = melangerToutesConjugaisons(conjugaisonsMelangees, conjugaisonsIncorrectes);
    
        for (int i = 0; i < optionsButtons.length; i++) {
            optionsButtons[i].setText(toutesConjugaisons[i]);
        }
    
        resultatLabel.setText("");
    }
    
    private static String[] genererConjugaisonsIncorrectes(String[] conjugaisonsCorrectes) {
        // Cette méthode génère des conjugaisons incorrectes de manière simple (à adapter en fonction de vos besoins)
        String[] conjugaisonsIncorrectes = new String[conjugaisonsCorrectes.length];
        
        for (int i = 0; i < conjugaisonsCorrectes.length; i++) {
            conjugaisonsIncorrectes[i] = "je parlons" ;
              conjugaisonsIncorrectes[i] = "je mangez" ;
                conjugaisonsIncorrectes[i] = "tu parle" ;
                   conjugaisonsIncorrectes[i] = "vous coiffe" ;
                   conjugaisonsIncorrectes[i] = "vous coiffe";
                    conjugaisonsIncorrectes[i] = "il lis" ;
                      conjugaisonsIncorrectes[i] = "je chante" ;
                        conjugaisonsIncorrectes[i] = "nous jouez" ;
                       conjugaisonsIncorrectes[i] = "je lit" ;
                          conjugaisonsIncorrectes[i] = "vous dancez";
                         conjugaisonsIncorrectes[i] = "je peut" ;
        }
    
        return melangerConjugaisons(conjugaisonsIncorrectes);
    }
    
    private static String[] melangerToutesConjugaisons(String[] conjugaisonsCorrectes, String[] conjugaisonsIncorrectes) {
        // Fusionner et mélanger les conjugaisons correctes et incorrectes
        String[] toutesConjugaisons = new String[conjugaisonsCorrectes.length + conjugaisonsIncorrectes.length];
        
        System.arraycopy(conjugaisonsCorrectes, 0, toutesConjugaisons, 0, conjugaisonsCorrectes.length);
        System.arraycopy(conjugaisonsIncorrectes, 0, toutesConjugaisons, conjugaisonsCorrectes.length, conjugaisonsIncorrectes.length);
    
        return melangerConjugaisons(toutesConjugaisons);
    }
    
    private static String[] melangerConjugaisons(String[] conjugaisons) {
        Random random = new Random();
        for (int i = conjugaisons.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Échange des positions
            String temp = conjugaisons[i];
            conjugaisons[i] = conjugaisons[index];
            conjugaisons[index] = temp;
        }
        return conjugaisons;
    }

    private static void verifierReponse(String reponseUtilisateur) {
    String labelText = verbeLabel.getText();
    String verbeInfinitif = labelText.substring(labelText.indexOf(":") + 2); // Récupérer le verbe sans le préfixe

    String[] conjugaisons = verbes.get(verbeInfinitif);

    if (Arrays.asList(conjugaisons).contains(reponseUtilisateur)) {
        resultatLabel.setForeground(new Color(0, 8, 200)); // Vert foncé
        resultatLabel.setText("Bravo ! c'est correcte +1 point");
         resultatLabel.setFont(new Font("Arial", Font.BOLD, 20));
        score++;
    } else {
        resultatLabel.setForeground(Color.RED);
        resultatLabel.setText("Incorrect essaie une autre fois .");
         resultatLabel.setFont(new Font("Arial", Font.BOLD, 20));
    }

    // Pause pour montrer le résultat pendant quelques secondes avant de passer au verbe suivant
    SwingUtilities.invokeLater(() -> {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        afficherNouveauVerbe();
    });
}
    }

