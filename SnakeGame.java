package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class SnakeGame extends JFrame {
    private static final int GRID_SIZE = 20;
    private static final int CELL_SIZE = 20;
    private static final int GAME_SPEED = 150;

    private LinkedList<Point> snake;
    private int direction; // 0: right, 1: down, 2: left, 3: up
    private Point food;

    public SnakeGame() {
        setTitle("Snake Game");
        setSize(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        snake = new LinkedList<>();
        snake.add(new Point(5, 5)); // Initial position of the snake
        direction = 0; // Initial direction: right

        spawnFood();

        Timer timer = new Timer(GAME_SPEED, new ActionListener() {
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
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direction != 1) direction = 3;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 3) direction = 1;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != 0) direction = 2;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 2) direction = 0;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setFocusable(true);
    }

    private void spawnFood() {
        int x = (int) (Math.random() * GRID_SIZE);
        int y = (int) (Math.random() * GRID_SIZE);
        food = new Point(x, y);
    }

    private void update() {
        Point head = snake.getFirst();
        Point newHead;

        switch (direction) {
            case 0: // right
                newHead = new Point((head.x + 1) % GRID_SIZE, head.y);
                break;
            case 1: // down
                newHead = new Point(head.x, (head.y + 1) % GRID_SIZE);
                break;
            case 2: // left
                newHead = new Point((head.x - 1 + GRID_SIZE) % GRID_SIZE, head.y);
                break;
            case 3: // up
                newHead = new Point(head.x, (head.y - 1 + GRID_SIZE) % GRID_SIZE);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

        // Check collision with food
        if (newHead.equals(food)) {
            snake.addFirst(food);
            spawnFood();
        } else {
            snake.addFirst(newHead);
            snake.removeLast();
        }

        // Check collision with itself
        for (int i = 1; i < snake.size(); i++) {
            if (newHead.equals(snake.get(i))) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0, 0, getWidth(), getHeight());

        // Draw food
        g.setColor(Color.RED);
        g.fillRect(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Draw snake
        g.setColor(Color.GREEN);
        for (Point point : snake) {
            g.fillRect(point.x * CELL_SIZE, point.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeGame().setVisible(true);
            }
        });
    }
}
