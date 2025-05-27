package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrosswordGame extends JFrame {
    private String[][] crossword = {
            { "S", "P", "R", "I", "N", "G" },
            { "C", "O", "D", "I", "N", "G" },
            { "J", "A", "V", "A", "R", "U", "L", "E", "S" },
            { "G", "U", "I", "T", "A", "R" },
            { "M", "O", "U", "S", "E" }
    };

    private JTextField[][] crosswordFields;

    public CrosswordGame() {
        setTitle("Crossword Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(crossword.length, crossword[0].length));

        crosswordFields = new JTextField[crossword.length][];

        for (int i = 0; i < crossword.length; i++) {
            crosswordFields[i] = new JTextField[crossword[i].length];
            for (int j = 0; j < crossword[i].length; j++) {
                crosswordFields[i][j] = new JTextField(1);
                crosswordFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                add(crosswordFields[i][j]);
            }
        }

        JButton checkButton = new JButton("Check");
        add(checkButton);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswers();
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void checkAnswers() {
        int correctAnswers = 0;

        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[i].length; j++) {
                String userAnswer = crosswordFields[i][j].getText().toUpperCase();
                if (userAnswer.equals(crossword[i][j])) {
                    crosswordFields[i][j].setBackground(Color.GREEN);
                    crosswordFields[i][j].setEditable(false);
                    correctAnswers++;
                } else {
                    crosswordFields[i][j].setBackground(Color.RED);
                }
            }
        }

        JOptionPane.showMessageDialog(this, "You got " + correctAnswers + " correct answers!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CrosswordGame();
            }
        });
    }
}
