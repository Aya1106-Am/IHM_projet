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

public class QuizSportDef extends JFrame {
    private JLabel sportLabel;
    private JButton[] choiceButtons;
    private Map<String, String[]> sportChoicMap;
    private String correctAnswer;

    private Timer timer;
    private int secondsLeft;
    private int score;
    private static int MAX_QUESTIONS = 0;

    public QuizSportDef() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        sportLabel = new JLabel();
        sportLabel.setForeground(Color.white);
        sportLabel.setFont(new Font("Arial", Font.BOLD, 29));
        sportLabel.setBounds(10, 90, 700, 30);
        add(sportLabel);

        setupChoiceButtons();

        sportChoicMap = new HashMap<>();
        sportChoicMap.put("yellow ball", new String[] { "Basketball", "Badminton", "Tennis" });
        sportChoicMap.put("bicycle", new String[] { "Skiing", "Tennis", "Cycling", });
        sportChoicMap.put("flat object", new String[] { "Archery", "Frisbee", "Skiing" });
        sportChoicMap.put("hands", new String[] { "FootBall", "Baseball", "Handball" });
        sportChoicMap.put("bow to shoot", new String[] { "Cycling", "Archery", "Tennis" });
        sportChoicMap.put("iron balls", new String[] { "Bocce Ball", "Badminton", "Baseball" });
        sportChoicMap.put("ball and stick", new String[] { "Frisbee", "Golf", "Football" });
        sportChoicMap.put("ice skates", new String[] { "Baseball", "Cycling", "Hockey" });
        sportChoicMap.put("gloves", new String[] { "Box", "Badminton", "Tennis" });
        sportChoicMap.put("ball in water", new String[] { "Golf", "Football", "Water Polo" });

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
        Object[] countries = sportChoicMap.keySet().toArray();
        String randomCountry = (String) countries[(int) (Math.random() * countries.length)];

        sportLabel.setText("which sport we play with a " + randomCountry + "?");

        String[] choices = sportChoicMap.get(randomCountry);

        for (int i = 0; i < 3; i++) {
            choiceButtons[i].setText(choices[i]);
        }
    }

    private void checkAnswer(String userAnswer) {
        String currentCountry = sportLabel.getText().replaceAll("which sport we play with a (.+)\\?", "$1");
        // La première option est la bonne

        if (currentCountry.equals("bow to shoot")) {
            correctAnswer = sportChoicMap.get(currentCountry)[1];
        }
        if (currentCountry.equals("yellow ball")) {
            correctAnswer = sportChoicMap.get(currentCountry)[2];
        }
        if (currentCountry.equals("bicycle")) {
            correctAnswer = sportChoicMap.get(currentCountry)[2];
        }
        if (currentCountry.equals("flat object")) {
            correctAnswer = sportChoicMap.get(currentCountry)[1];
        }
        if (currentCountry.equals("iron balls")) {
            correctAnswer = sportChoicMap.get(currentCountry)[0];
        }
        if (currentCountry.equals("hands")) {
            correctAnswer = sportChoicMap.get(currentCountry)[2];
        }
         if (currentCountry.equals("ball and stick")) {
            correctAnswer = sportChoicMap.get(currentCountry)[1];
        }
         if (currentCountry.equals("ice skates")) {
            correctAnswer = sportChoicMap.get(currentCountry)[2];
        }
         if (currentCountry.equals("gloves")) {
            correctAnswer = sportChoicMap.get(currentCountry)[0];
        }
        if (currentCountry.equals("ball in water")) {
            correctAnswer = sportChoicMap.get(currentCountry)[2];
        }

        if (userAnswer.equals(correctAnswer)) {
            MAX_QUESTIONS = MAX_QUESTIONS + 1;
            JOptionPane.showMessageDialog(this, "Correct! The Sport  " + currentCountry + " is " + correctAnswer);
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
            setTitle("Sport Guess Game - Time Left: " + secondsLeft + "s");
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
                QuizSportDef game = new QuizSportDef();
                game.setTitle("Sport Guess Game Defecult");
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
