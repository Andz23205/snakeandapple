package btljava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    Timer timer;
    Snake snake;
    Apple apple;
    Random random;

    boolean running = false;
    int applesEaten = 0;
    int speed = 1;
    static final int MAX_SPEED = 8;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        snake = new Snake();
        apple = new Apple(random);
        applesEaten = 0;
        speed = 1;
        running = true;

        timer = new Timer(120, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {

            g.setColor(Color.red);
            g.fillOval(apple.x, apple.y, UNIT_SIZE, UNIT_SIZE);

            snake.draw(g);

            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten,
                    (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2,
                    g.getFont().getSize());

        } else {
            gameOver(g);
        }
    }

    public void updateSpeed() {

        if (speed >= MAX_SPEED) return;

        if (speed < 4) {
            if (applesEaten % 3 == 0) speed++;
        } else {
            if ((applesEaten - 9) % 10 == 0) speed++;
        }

        if (speed > MAX_SPEED) speed = MAX_SPEED;

        timer.setDelay(120 - speed * 10);
    }

    public void checkApple() {

        if (snake.getHeadX() == apple.x &&
            snake.getHeadY() == apple.y) {

            snake.grow();
            applesEaten++;
            apple.randomize();
            updateSpeed();
        }
    }

    public void checkCollisions() {

        if (snake.checkBodyCollision() ||
            snake.getHeadX() < 0 ||
            snake.getHeadX() >= SCREEN_WIDTH ||
            snake.getHeadY() < 0 ||
            snake.getHeadY() >= SCREEN_HEIGHT) {

            running = false;
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics metrics1 = getFontMetrics(g.getFont());

        g.drawString("Game Over",
                (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2,
                SCREEN_HEIGHT / 2);

        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        FontMetrics metrics2 = getFontMetrics(g.getFont());

        g.drawString("Press R to Restart",
                (SCREEN_WIDTH - metrics2.stringWidth("Press R to Restart")) / 2,
                SCREEN_HEIGHT / 2 + 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (!running && e.getKeyCode() == KeyEvent.VK_R) {
                startGame();
            }

            snake.changeDirection(e.getKeyCode());
        }
    }
}