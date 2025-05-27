package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private int paddleY = HEIGHT / 2;
    private int paddleSpeed = 5;

    private int ballX = WIDTH / 2;
    private int ballY = HEIGHT / 2;
    private int ballSpeedX = -3;
    private int ballSpeedY = 2;

    public PongGame() {
        setTitle("Pong Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP && paddleY > 0) {
                    paddleY -= paddleSpeed;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && paddleY < HEIGHT - 50) {
                    paddleY += paddleSpeed;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setFocusable(true);
    }

    private void update() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        if (ballY <= 0 || ballY >= HEIGHT - 20) {
            ballSpeedY = -ballSpeedY;
        }

        if (ballX <= 0 || ballX >= WIDTH - 20) {
            ballSpeedX = -ballSpeedX;
        }

        if (ballX <= 20 && ballY >= paddleY && ballY <= paddleY + 50) {
            ballSpeedX = -ballSpeedX;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.fillRect(10, paddleY, 10, 50);
        g.fillOval(ballX, ballY, 20, 20);

        Toolkit.getDefaultToolkit().sync();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PongGame().setVisible(true);
            }
        });
    }
}
