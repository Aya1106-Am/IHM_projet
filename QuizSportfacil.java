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

public class QuizSportfacil extends JFrame {
    private JLabel sportLabel;
    private JButton[] choiceButtons;
    private Map<String, String[]> sportCoicesMap; // Utiliser un tableau de chaînes pour les choix
    private String correctAnswer;

    public QuizSportfacil() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        sportLabel = new JLabel();
        sportLabel.setForeground(Color.white); // Changer la couleur du texte si nécessaire
        sportLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Changer la police du texte si nécessaire

        sportLabel.setBounds(10, 90, 700, 30); // Ajuster la position et la taille du label

        add(sportLabel);

        setupChoiceButtons();

        sportCoicesMap = new HashMap<>();
        sportCoicesMap.put("iron balls", new String[] { "Bocce Ball", "Badminton", "Baseball" });
        sportCoicesMap.put("ice skates", new String[] { "Baseball", "Cycling", "Hockey" });
        sportCoicesMap.put("hands", new String[] { "FootBall", "Handball", "Skiing" });
        sportCoicesMap.put("gloves", new String[] { "Tennis", "Basketball", "Box" });
        sportCoicesMap.put("bicycle", new String[] { "Box", "Cycling", "Football" });
        sportCoicesMap.put("yellow ball", new String[] { "Tennis", "Basketball", "Badminton" });
 

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
        Object[] countries = sportCoicesMap.keySet().toArray();
        String randomCountry = (String) countries[(int) (Math.random() * countries.length)];

        sportLabel.setText("which sport we play with a " + randomCountry + "?");

        String[] choices = sportCoicesMap.get(randomCountry);

        for (int i = 0; i < 3; i++) {
            choiceButtons[i].setText(choices[i]);
        }
    }

    private void checkAnswer(String userAnswer) {
        String currentCountry = sportLabel.getText().replaceAll("which sport we play with a (.+)\\?", "$1");
        // La première option est la bonne

        if (currentCountry.equals("bicycle")) {
            correctAnswer = sportCoicesMap.get(currentCountry)[1];
        }
        if (currentCountry.equals("iron balls")) {
            correctAnswer = sportCoicesMap.get(currentCountry)[0];
        }
        if (currentCountry.equals("ice skates")) {
            correctAnswer = sportCoicesMap.get(currentCountry)[2];
        }
        if (currentCountry.equals("hands")) {
            correctAnswer = sportCoicesMap.get(currentCountry)[1];
        }
        if (currentCountry.equals("yellow ball")) {
            correctAnswer = sportCoicesMap.get(currentCountry)[0];
        }
        if (currentCountry.equals("gloves")) {
            correctAnswer = sportCoicesMap.get(currentCountry)[2];
        }
      

        if (userAnswer.equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Correct! The sport " + currentCountry + " is " + correctAnswer);
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
                QuizSportfacil game = new QuizSportfacil();
                game.setTitle("Sport Guess Game facile");
                game.setSize(600, 400);
                BufferedImage backgroundImage = ImageIO.read(new File("sport.png"));
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
