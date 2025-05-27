package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MotsMysteresJeuSansPhoto extends JFrame {
    private List<String> mots;
    private String motCourant;
    private StringBuilder motCache;
    private int tempsRestant;
    private Timer timer;

    private JLabel motLabel;
    private JTextField saisieLettreField;
    private JButton devinerButton;

    public MotsMysteresJeuSansPhoto() {
        mots = new ArrayList<>();
        Collections.addAll(mots, "chat", "maison", "voiture", "arbre");

        setTitle("Mots Mystères");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        motLabel = new JLabel();
        add(motLabel, BorderLayout.CENTER);

        saisieLettreField = new JTextField();
        add(saisieLettreField, BorderLayout.SOUTH);

        devinerButton = new JButton("Deviner");
        devinerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierLettre();
            }
        });
        add(devinerButton, BorderLayout.EAST);

        initialiserPartie();
    }

    private void initialiserPartie() {
        Collections.shuffle(mots);
        motCourant = mots.get(0);
        motCache = new StringBuilder("_".repeat(motCourant.length()));
        afficherMotCache();
        saisieLettreField.setText("");
        demarrerTimer();
    }

    private void afficherMotCache() {
        motLabel.setText(motCache.toString());
    }

    private void demarrerTimer() {
        tempsRestant = 60;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempsRestant--;
                if (tempsRestant <= 0) {
                    terminerPartie();
                }
            }
        });
        timer.start();
    }

    private void terminerPartie() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Le temps est écoulé. Le mot était : " + motCourant);
        initialiserPartie();
    }

    private void verifierLettre() {
        String lettre = saisieLettreField.getText().trim().toLowerCase();

        if (lettre.length() == 1) {
            boolean lettreCorrecte = false;
            for (int i = 0; i < motCourant.length(); i++) {
                if (motCourant.charAt(i) == lettre.charAt(0)) {
                    motCache.setCharAt(i, lettre.charAt(0));
                    lettreCorrecte = true;
                }
            }

            if (!lettreCorrecte) {
                JOptionPane.showMessageDialog(this, "Lettre incorrecte. Essayez à nouveau !");
            }

            afficherMotCache();

            if (motCache.toString().equals(motCourant)) {
                JOptionPane.showMessageDialog(this, "Bravo ! Vous avez deviné le mot.");
                initialiserPartie();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une seule lettre à la fois.");
        }

        saisieLettreField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MotsMysteresJeuSansPhoto().setVisible(true);
            }
        });
    }
}
