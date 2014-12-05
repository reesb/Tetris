/**
 * Created by Rees on 10/31/14.
 */
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Tetris extends JFrame {

    public final int SWAG_WIDTH = 1280;
    public final int SWAG_HEIGHT = 720;

    private Canvas board;

    public Tetris() {

        board = new Canvas(30, 30);
        add(board);

        setTitle("Dickbutt");
        setSize(SWAG_WIDTH, SWAG_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tetris tetris = new Tetris();
                tetris.setVisible(true);
            }
        });
    }
}