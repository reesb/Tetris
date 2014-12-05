import java.awt.*;

/**
 * Created by Rees on 11/18/14.
 */
public class Tetromino {

    public int x, y;
    public int[][] brick;

    public Tetromino(int x, int y, int[][] brick) {

        this.x = x;
        this.y = y;
        this.brick = brick;
    }

    public void draw(Graphics g) {
        g.drawRect(x, y, 50, 50);
    }
}
