package btljava;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.add(new GamePanel());
        frame.setTitle("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}