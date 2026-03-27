package btljava;
import java.awt.Color;
import java.awt.Graphics;

public class Snake {

    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = 576;

    int x[] = new int[GAME_UNITS];
    int y[] = new int[GAME_UNITS];
    int bodyParts = 3;
    char direction = 'R';

    public Snake() {
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 100 - i * UNIT_SIZE;
            y[i] = 100;
        }
    }
    public void wrap() {
    if (x[0] < 0) x[0] = 600 - UNIT_SIZE;
    else if (x[0] >= 600) x[0] = 0;

    if (y[0] < 0) y[0] = 600 - UNIT_SIZE;
    else if (y[0] >= 600) y[0] = 0;
}
    public void move() {

        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U': y[0] -= UNIT_SIZE; break;
            case 'D': y[0] += UNIT_SIZE; break;
            case 'L': x[0] -= UNIT_SIZE; break;
            case 'R': x[0] += UNIT_SIZE; break;
        }
    }

    public void grow() {
        bodyParts++;
    }

    public void changeDirection(int keyCode) {

        switch (keyCode) {
            case 37: if (direction != 'R') direction = 'L'; break;
            case 38: if (direction != 'D') direction = 'U'; break;
            case 39: if (direction != 'L') direction = 'R'; break;
            case 40: if (direction != 'U') direction = 'D'; break;
        }
    }

    public boolean checkBodyCollision() {
        for (int i = bodyParts - 1; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                return true;
            }
        }
        return false;
    }

public void draw(Graphics g, Color[] rainbow, int offset) {

    for (int i = 0; i < bodyParts; i++) {

        int colorIndex = (i + offset / 2) % rainbow.length;
        g.setColor(rainbow[colorIndex]);

        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
    }
}
    public int getHeadX() { return x[0]; }
    public int getHeadY() { return y[0]; }
}