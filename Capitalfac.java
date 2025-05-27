import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Capitalfac extends JFrame {
    private JLabel countryLabel;
    private JButton[] choiceButtons;
    private Map<String, String[]> capitalChoices; // Utiliser un tableau de chaînes pour les choix
    private String correctAnswer;

    public Capitalfac() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        countryLabel = new JLabel();
        countryLabel.setForeground(Color.white); // Changer la couleur du texte si nécessaire
        countryLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Changer la police du texte si nécessaire

        countryLabel.setBounds(100, 90, 500, 30); // Ajuster la position et la taille du label

        add(countryLabel);

        setupChoiceButtons();

        capitalChoices = new HashMap<>();
        capitalChoices.put("France", new String[] { "Marseille", "Berlin", "Paris" });
        capitalChoices.put("Germany", new String[] { "Hamburg", "Munich", "Berlin", });
        capitalChoices.put("Italy", new String[] { "Milan", "Rome", "Naples" });
        capitalChoices.put("Japan", new String[] { "Osaka", "Kyoto", "Tokyo" });
        capitalChoices.put("Algeria", new String[] { "Oran", "Alger", "Stif" });
        capitalChoices.put("Qatar", new String[] { "Doha", "Milan", "Riad" });
  displayNextQuestion();
       
       
    }

    private void setupChoiceButtons() {
        JPanel choicesPanel = new JPanel(new FlowLayout()); // Utiliser FlowLayout au lieu de GridLayout
        choicesPanel.setOpaque(false);
        choiceButtons = new JButton[3];

        for (int i = 0; i < 3; i++) {
            choiceButtons[i] = new JButton();
            choiceButtons[i].setPreferredSize(new Dimension(120, 90));
            choiceButtons[i].setBackground(Color.white); // Changer la couleur selon vos préférences

            choiceButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    String userAnswer = sourceButton.getText();
                    checkAnswer(userAnswer);
                    displayNextQuestion();
                }
            });
            choicesPanel.add(choiceButtons[i]);
        }

        
        choicesPanel.setBounds(70, 150, 450, 100);
        // Ajuster la position et la taille du panel des choix
        add(choicesPanel);
    }

    private void displayNextQuestion() {
        Object[] countries = capitalChoices.keySet().toArray();
        String randomCountry = (String) countries[(int) (Math.random() * countries.length)];

        countryLabel.setText("What is the capital of " + randomCountry + "?");

        String[] choices = capitalChoices.get(randomCountry);

        for (int i = 0; i < 3; i++) {
            choiceButtons[i].setText(choices[i]);
        }
    }

    private void checkAnswer(String userAnswer) {
        String currentCountry = countryLabel.getText().replaceAll("What is the capital of (.+)\\?", "$1");
        // La première option est la bonne

        if (currentCountry.equals("Algeria")) {
            correctAnswer = capitalChoices.get(currentCountry)[1];
        }
        if (currentCountry.equals("France")) {
            correctAnswer = capitalChoices.get(currentCountry)[2];
        }
        if (currentCountry.equals("Germany")) {
            correctAnswer = capitalChoices.get(currentCountry)[2];
        }
        if (currentCountry.equals("Italy")) {
            correctAnswer = capitalChoices.get(currentCountry)[1];
        }
        if (currentCountry.equals("Qatar")) {
            correctAnswer = capitalChoices.get(currentCountry)[0];
        }
        if (currentCountry.equals("Japan")) {
            correctAnswer = capitalChoices.get(currentCountry)[2];
        }
      

        if (userAnswer.equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Correct! The capital of " + currentCountry + " is " + correctAnswer);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is " + correctAnswer);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                creerEtAfficherUI();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
        
        static void creerEtAfficherUI() throws IOException{
                Capitalfac game = new Capitalfac();
                game.setTitle("Capital Guess Game facile");
                game.setSize(600, 400);
                BufferedImage backgroundImage = ImageIO.read(new File("geo.png"));
                JPanel imagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
                };
                imagePanel.setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
                imagePanel.setOpaque(false); // Rendre le panel transparent
                game.add(imagePanel);

                game.setResizable(false);
                game.setLocationRelativeTo(null);// rendre dans le centre

                game.setVisible(true);
           
    }
}
