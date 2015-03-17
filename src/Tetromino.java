import java.awt.*;

/**
 * Created by Rees on 11/18/14.
 */
public class Tetromino {

    public int x, y;
    public boolean[][] brick;
    public Block type;

    public Tetromino(int x, int y, boolean[][] brick, Block type) {

        this.x = x;
        this.y = y;
        this.brick = brick;
        this.type = type;

    }

    public void draw(int boardX, int boardY, int blockSize, Graphics g) {
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[i].length; j++) {
                if (brick[i][j]) {
                    type.draw(boardX, boardY, (x + i), (y + j), blockSize, g);
                }
            }
        }
    }

    public void moveDown() {
        y += 1;
    }

    public void moveLeft() {
        x -= 1;
    }

    public void moveRight() {
        x += 1;
    }


}
