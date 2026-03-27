package btljava;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;

    boolean gameStarted = false;

    javax.swing.Timer timer;
    Snake snake;
    Apple apple;
    Random random;

    boolean running = false;
    int applesEaten = 0;

    int speed = 1;
    static final int MAX_SPEED = 8;

    ArrayList<Brick> bricks = new ArrayList<>();
    int brickSpawnTimer = 0;
    static final int MAX_BRICKS = 20;

    float colorShift = 0;

    public GamePanel() {
        random = new Random();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
    }

    public void startGame() {
        snake = new Snake();
        apple = new Apple(random);
        applesEaten = 0;
        speed = 1;
        running = true;
        bricks.clear();

        if (timer != null) timer.stop();
     timer = new javax.swing.Timer(100, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(40, 40, 40));

for (int i = 0; i <= SCREEN_WIDTH; i += UNIT_SIZE) {
    g.drawLine(i, 0, i, SCREEN_HEIGHT);
}

for (int i = 0; i <= SCREEN_HEIGHT; i += UNIT_SIZE) {
    g.drawLine(0, i, SCREEN_WIDTH, i);
}
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (!gameStarted) {
            g2.setColor(Color.green);
            g2.setFont(new Font("Ink Free", Font.BOLD, 50));
            g2.drawString("SNAKE GAME", 120, 250);

            g2.setFont(new Font("Ink Free", Font.BOLD, 25));
            g2.setColor(Color.white);
            g2.drawString("Press ENTER to Start", 150, 320);
            return;
        }

        if (running) {

            // apple
            g2.setColor(Color.red);
            g2.fillOval(apple.x, apple.y, UNIT_SIZE, UNIT_SIZE);

            // brick
            g2.setColor(Color.white);
            for (Brick b : bricks) {
                g2.fillRect(b.x, b.y, UNIT_SIZE, UNIT_SIZE);
            }

            drawSmoothSnake(g2);

            // score
            g2.setColor(Color.white);
            g2.setFont(new Font("Ink Free", Font.BOLD, 30));
            g2.drawString("Score: " + applesEaten, 20, 40);

        } else {
            gameOver(g2);
        }
    }

    private void drawSmoothSnake(Graphics2D g2) {

       Path2D path = new Path2D.Float();

boolean started = false;

for (int i = 0; i < snake.bodyParts - 1; i++) {

    int x1 = snake.x[i];
    int y1 = snake.y[i];

    int x2 = snake.x[i + 1];
    int y2 = snake.y[i + 1];

    // ❗ nếu warp thì NGẮT đoạn
    if (Math.abs(x1 - x2) > UNIT_SIZE * 2 ||
        Math.abs(y1 - y2) > UNIT_SIZE * 2) {

        started = false;
        continue;
    }

    if (!started) {
        path.moveTo(x1 + UNIT_SIZE / 2, y1 + UNIT_SIZE / 2);
        started = true;
    }

    path.lineTo(x2 + UNIT_SIZE / 2, y2 + UNIT_SIZE / 2);
}

        g2.setColor(Color.green);
        g2.setStroke(new BasicStroke(UNIT_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(path);
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
        if (snake.getHeadX() == apple.x && snake.getHeadY() == apple.y) {
            snake.grow();
            applesEaten++;
            apple.randomize();
            updateSpeed();
            spawnRandomBricks(2);
        }
    }

    public void checkCollisions() {

        if (brickActiveCollision()) return;

        if (snake.checkBodyCollision() ||
                snake.getHeadX() < 0 || snake.getHeadX() >= SCREEN_WIDTH ||
                snake.getHeadY() < 0 || snake.getHeadY() >= SCREEN_HEIGHT) {

            running = false;
            timer.stop();
        }
    }

    private boolean brickActiveCollision() {
        for (Brick b : bricks) {
            if (snake.getHeadX() == b.x && snake.getHeadY() == b.y) {
                running = false;
                timer.stop();
                return true;
            }
        }
        return false;
    }

    public void spawnRandomBricks(int amount) {
        for (int i = 0; i < amount; i++) {
            if (bricks.size() >= MAX_BRICKS) break;

            int x = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
            int y = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;

            bricks.add(new Brick(x, y));
        }
    }

    private void autoSpawnBrick() {
        brickSpawnTimer++;
        if (brickSpawnTimer >= 15) {
            spawnRandomBricks(1);
            brickSpawnTimer = 0;
        }
    }

    public void gameOver(Graphics g) {

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        g.drawString("Game Over", 180, 300);

        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        g.drawString("Press R to Restart", 180, 350);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameStarted && running) {
            snake.move();
            snake.wrap(); 
            checkApple();
            checkCollisions();
            autoSpawnBrick();
        }

        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            if (!gameStarted && e.getKeyCode() == KeyEvent.VK_ENTER) {
                gameStarted = true;
                startGame();
                return;
            }

            if (!running && e.getKeyCode() == KeyEvent.VK_R) {
                startGame();
            }

            if (snake != null) {
                snake.changeDirection(e.getKeyCode());
            }
        }
    }

    static class Brick {
        int x, y;
        Brick(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}