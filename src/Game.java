import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rees on 11/18/14.
 */
public class Game {
    private int score = 0;

    private Block[][] board;

    private boolean[][] zPiece = {
            {false, false, true, false},
            {false, true, true, false},
            {false, true, false, false},
            {false ,false, false, false}};

    private boolean[][] sPiece = {
            {false, true, false, false},
            {false, true, true, false},
            {false, false, true, false},
            {false, false, false, false}};

    private boolean[][] iPiece = {      // apple's new gizmo
            {false, false, true, false},
            {false, false, true, false},
            {false, false, true, false},
            {false, false, true, false}};

    private boolean[][] squarePiece = {
            {false, false, false, false},
            {false, true, true, false},
            {false, true, true, false},
            {false, false, false, false}};

    private boolean[][] lPiece = {
            {false, false, false, false},
            {false, true, false, false},
            {false, true, true, true},
            {false, false, false, false}};

    private boolean[][] jPiece = {
            {false, false, false, false},
            {false, false, false, true},
            {false, true, true, true},
            {false, false, false, false}};

    private boolean[][] tPiece = {
            {false, false, false, false},
            {false, false, true, false},
            {false, true, true, true},
            {false, false, false, false}};

    private static boolean[][][] pieces = new boolean[7][4][4];
    private static Color[] colors = new Color[7];

    private Tetromino currentPiece;

    private int blockSize = 24;
    private int boardX = 0 * blockSize;
    private int boardY = -2 * blockSize;
    private int boardWidth = 10, boardHeight = 28;

    private BufferedImage grid = null;
    //private BufferedImage boardImg = null;

    public Game() {

        try {
            grid = ImageIO.read(new File("src/img/Grid.png"));
        } catch (IOException e) {
            System.out.println("Grid Image File not found at 'src/img/Grid.png' because u w0t m8 1v1 mi irl in cod farmville m8 ill fkn noscope ur mum lel git rekt lawlawlawlawlawlawlawl get slammed");
            System.exit(1);
        }

        this.board = new Block[boardWidth][boardHeight];

        pieces[0] = zPiece;
        pieces[1] = sPiece;
        pieces[2] = iPiece;
        pieces[3] = squarePiece;
        pieces[4] = lPiece;
        pieces[5] = jPiece;
        pieces[6] = tPiece;

        colors[0] = Color.RED;
        colors[1] = Color.CYAN;
        colors[2] = Color.BLACK;
        colors[3] = Color.BLUE;
        colors[4] = Color.MAGENTA;
        colors[5] = Color.GREEN;
        colors[6] = Color.ORANGE;

        newPiece();


    }

    public void checkLine () {
        boolean fullLine;
        for (int i = 0; i < board[0].length; i++) {
            fullLine = true;
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == null) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {

                score(1337);

                for (int j = 0; j < board.length; j++) {
                    board[j][i] = null;
                }
                for (int k = 0; k < board.length; k++) {
                    for (int l = i; l > 0; l--) {
                        if (l > 0) {
                            board[k][l] = board[k][l - 1];
                        } else {
                            board[k][l] = null;
                        }
                    }
                }
            }
        }
    }

    public void moveBlock () {
        currentPiece.y++;
        checkCollision();
    }

    public void place (Tetromino piece) {
        System.out.println("Is Placed");
        for (int i = 0; i < piece.brick.length; i++) {
            for (int j = 0; j < piece.brick[0].length; j++) {
                if (piece.brick[i][j]) {
                    board[piece.x + i][piece.y + j] = piece.type;
                }
            }
        }
        checkLine();
        newPiece();
    }

    public boolean checkCollision () {
        for (int i = 0; i < currentPiece.brick.length; i++) {
            for (int j = 0; j < currentPiece.brick[0].length; j++) {
                if (currentPiece.brick[i][j]) {
                    if (currentPiece.x + i > board.length - 1 || currentPiece.x + i < 0) {
                        return true;
                    }
                    if (currentPiece.y + j > board[0].length - 1) {
                        return true;
                    }

                    if (currentPiece.y + j >= 0) {
                        if (board[currentPiece.x + i][currentPiece.y + j] != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void draw (Graphics g) {
        //g.drawImage(boardImg, 0, 0, null);

        //draw score
        g.setFont(new Font("Comic Sans MS", 1, 30));
        g.drawString("Score: " + score, 400, 200);

        drawBorder(g);

        //draw grid
        g.drawImage(grid, boardX, boardY, null);
        //other stuff

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].draw(boardX, boardY, i, j, blockSize, g);
                }
            }
        }

        currentPiece.draw(boardX, boardY, blockSize, g);
    }

    public void newPiece() {
        int number = (int) (Math.random() * 7);
        currentPiece = new Tetromino(boardWidth / 2 - 2, 0, pieces[number], new Block(colors[number]));
    }

    public void tick () {
        currentPiece.moveDown();
        if (checkCollision()) {
            currentPiece.y--;
            place(currentPiece);
        }
    }

    public void movePieceLeft() {
        currentPiece.moveLeft();
        if(checkCollision()) {
            currentPiece.moveRight();
        }
    }

    public void movePieceRight() {
        currentPiece.moveRight();
        if(checkCollision()) {
            currentPiece.moveLeft();
        }
    }

    public void reset() {
        board = new Block[boardWidth][boardHeight];
        newPiece();
        score = 0;
    }

    public boolean[][] rotateCW(boolean[][] piece) {

        boolean[][] rotatedPiece = new boolean[piece.length][piece[0].length];

        for (int i = 0; i < piece.length; i++) {
            for(int j = 0; j < piece[0].length; j++) {
                rotatedPiece[-j + piece[0].length - 1][i] = piece[i][j];
            }
        }
        return rotatedPiece;
    }

    public boolean[][] rotateCCW(boolean[][] piece) {

        boolean[][] rotatedPiece = new boolean[piece.length][piece[0].length];

        for (int i = 0; i < piece.length; i++) {
            for(int j = 0; j < piece[0].length; j++) {
                rotatedPiece[j][-i + piece[0].length - 1] = piece[i][j];
            }
        }
        return rotatedPiece;
    }

    public void rotatePieceCW() {
        currentPiece.brick = rotateCW(currentPiece.brick);
        if (checkCollision()) {
            if (!fixPiece()) {
                currentPiece.brick = rotateCW(currentPiece.brick);
            }
        }
    }

    public void rotatePieceCCW() {
        currentPiece.brick = rotateCCW(currentPiece.brick);
        if (checkCollision()) {
            if (!fixPiece()) {
                currentPiece.brick = rotateCW(currentPiece.brick);
            }
        }
    }

    private boolean fixPiece() {
        int currPosX = currentPiece.x;
        int currPosY = currentPiece.y;
        boolean success = false;
        for (int i = 0; i < 4; i++) {
            currentPiece.x = currPosX + (int) Math.round(Math.cos(i * (Math.PI/2)));
            currentPiece.y = currPosY + (int) Math.round(Math.sin(i * (Math.PI/2)));
            if (!checkCollision()) {
                success = true;
                break;
            }
        }
        return success;
    }

    public void hardDrop() {
        while (!checkCollision()) {
            currentPiece.moveDown();
        }
        currentPiece.y--;
        place(currentPiece);

    }

    public void drawBorder(Graphics g) {
        g.drawRect(boardX, boardY, board.length * blockSize, board[0].length * blockSize);

    }

    public void score(int value) {
        score = score + value;
    }
}
