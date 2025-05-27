import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class CapitaleDef extends JFrame {
    private JLabel countryLabel;
    private JButton[] choiceButtons;
    private Map<String, String[]> capitalChoices;
    private String correctAnswer;

    private Timer timer;
    private int secondsLeft;
    private int score;
    private static int MAX_QUESTIONS = 0;

    public CapitaleDef() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        countryLabel = new JLabel();
        countryLabel.setForeground(Color.white);
        countryLabel.setFont(new Font("Arial", Font.BOLD, 30));
        countryLabel.setBounds(100, 90, 500, 30);
        add(countryLabel);

        setupChoiceButtons();

        capitalChoices = new HashMap<>();
        capitalChoices.put("France", new String[] { "Marseille", "Berlin", "Paris" });
        capitalChoices.put("Germany", new String[] { "Hamburg", "Munich", "Berlin", });
        capitalChoices.put("Italy", new String[] { "Milan", "Rome", "Naples" });
        capitalChoices.put("Japan", new String[] { "Osaka", "Kyoto", "Tokyo" });
        capitalChoices.put("Algeria", new String[] { "Oran", "Alger", "Stif" });
        capitalChoices.put("Qatar", new String[] { "Doha", "Milan", "Riad" });
        capitalChoices.put("Spain", new String[] { "Séville", "Madrid", "Ségovie" });
        capitalChoices.put("Turquie", new String[] { "Istanbul", "Izmir", "Ankara" });
        capitalChoices.put("Russie", new String[] { "Moscou", "Kazan", "Tomsk" });
        capitalChoices.put("Brésil", new String[] { "Recife", "Manaus", "Brasília" });

        displayNextQuestion();

        // Initialiser le chronomètre pour 60 secondes
        secondsLeft = 60;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimerLabel();
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    private void setupChoiceButtons() {
        JPanel choicesPanel = new JPanel(new FlowLayout());
        choicesPanel.setOpaque(false);
        choiceButtons = new JButton[3];

        for (int i = 0; i < 3; i++) {
            choiceButtons[i] = new JButton();
            choiceButtons[i].setPreferredSize(new Dimension(120, 90));
            choiceButtons[i].setBackground(Color.white);

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
        if (currentCountry.equals("Japan")) {
            correctAnswer = capitalChoices.get(currentCountry)[2];
        }
         if (currentCountry.equals("Spain")) {
            correctAnswer = capitalChoices.get(currentCountry)[1];
        }
 if (currentCountry.equals("Turquie")) {
            correctAnswer = capitalChoices.get(currentCountry)[2];
        }
 if (currentCountry.equals("Russie")) {
            correctAnswer = capitalChoices.get(currentCountry)[0];
        }
        if (currentCountry.equals("Brésil")) {
            correctAnswer = capitalChoices.get(currentCountry)[2];
        }

        if (userAnswer.equals(correctAnswer)) {
            MAX_QUESTIONS = MAX_QUESTIONS + 1;
            JOptionPane.showMessageDialog(this, "Correct! The capital of " + currentCountry + " is " + correctAnswer);
        } else {
              MAX_QUESTIONS = MAX_QUESTIONS + 1;
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is " + correctAnswer);
        }
        if (userAnswer.equals(correctAnswer)) {
            score++;
        }
        displayNextQuestion();
    }

    private void updateTimerLabel() {
        secondsLeft--;
        if (secondsLeft >= 0) {
            setTitle("Capital Guess Game - Time Left: " + secondsLeft + "s");
        } else {
            timer.stop();
            showFinalScore();
        }
    }

    private void showFinalScore() {
        JOptionPane.showMessageDialog(this, "Your final score: " + score + " out of " + MAX_QUESTIONS);
        System.exit(0);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            creerEtAfficherUI();
        });
    }
   
    static void creerEtAfficherUI()    { try {
                CapitaleDef game = new CapitaleDef();
                game.setTitle("Capital Guess Game Defecult");
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
                imagePanel.setOpaque(false);
                game.add(imagePanel);

                game.setResizable(false);
                game.setLocationRelativeTo(null);

                game.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }
}
