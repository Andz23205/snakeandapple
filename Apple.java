package btljava;
import java.util.Random;

public class Apple {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;

    int x;
    int y;
    Random random;

    public Apple(Random random) {
        this.random = random;
        randomize();
    }

    public void randomize() {
        x = random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        y = random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }
}