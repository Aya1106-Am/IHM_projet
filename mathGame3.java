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

public class mathGame3 extends JFrame {
    private JLabel mathLabel;
    private JButton[] choiceButtons;
    private Map<String, String[]> answerChoices;
    private String correctAnswer;

    public mathGame3() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        mathLabel = new JLabel();
        mathLabel.setForeground(Color.white);
        mathLabel.setFont(new Font("Arial", Font.BOLD, 30));
        mathLabel.setBounds(100, 90, 500, 30);
        add(mathLabel);

        setupChoiceButtons();

        answerChoices = new HashMap<>();
        answerChoices.put("2+2", new String[]{"1", "3", "4"});
        answerChoices.put("2+7", new String[]{"9", "6", "8"});
        answerChoices.put("5-3", new String[]{"3", "2", "4"});
        answerChoices.put("4+2", new String[]{"7", "8", "6"});
        answerChoices.put("5+5", new String[]{"11", "10", "12"});
        answerChoices.put("9+3", new String[]{"10", "12", "13"});

        displayNextQuestion();
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
        Object[] equ = answerChoices.keySet().toArray();
        String randomanswer = (String) equ[(int) (Math.random() * equ.length)];

        mathLabel.setText("What is the result of " + randomanswer + "?");

        String[] choices = answerChoices.get(randomanswer);

        for (int i = 0; i < 3; i++) {
            choiceButtons[i].setText(choices[i]);
        }
    }

    private void checkAnswer(String userAnswer) {
        String currentequ = mathLabel.getText().replaceAll("What is the result of (.+)\\?", "$1");

        if (currentequ.equals("2+2")) {
            correctAnswer = answerChoices.get(currentequ)[2];
        }
        if (currentequ.equals("2+7")) {
            correctAnswer = answerChoices.get(currentequ)[0];
        }
        if (currentequ.equals("5-3")) {
            correctAnswer = answerChoices.get(currentequ)[1];
        }
        if (currentequ.equals("4+2")) {
            correctAnswer = answerChoices.get(currentequ)[2];
        }
        if (currentequ.equals("5+5")) {
            correctAnswer = answerChoices.get(currentequ)[1];
        }
        if (currentequ.equals("9+3")) {
            correctAnswer = answerChoices.get(currentequ)[1];
        }

        if (userAnswer.equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Correct! The answer of " + currentequ + " is " + correctAnswer);
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
                mathGame3 game = new mathGame3();
                game.setTitle("math Game facile");
                game.setSize(600, 400);
                BufferedImage backgroundImage = ImageIO.read(new File("3.jpg"));
                JPanel imagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Image resizedImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

                        g.drawImage(resizedImage, 0, 0, null);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
                };
                imagePanel.setSize(600, 400);
                imagePanel.setOpaque(false); // Rendre le panel transparent
                game.add(imagePanel);
                imagePanel.setLayout(null);
                game.setResizable(false);
                game.setLocationRelativeTo(null);// rendre dans le centre
                game.setSize(600, 400);
                game.setVisible(true);
       
            }     

     }     
    
    
