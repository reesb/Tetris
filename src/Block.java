import java.awt.*;

/**
 * Created by Rees on 11/21/14.
 */
public class Block {
    //Image sprite;
    Color color;
    public Block(Color color) {
        this.color = color;
    }

    public void draw (int boardX, int boardY, int x, int y, int blockSize, Graphics g) {
        g.setColor(color);
        g.fillRect(boardX + (x * blockSize), boardY + (y * blockSize), blockSize, blockSize);
    }
}
