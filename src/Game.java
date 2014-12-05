/**
 * Created by Rees on 11/18/14.
 */
public class Game {

    private Block[][] board;
    private Tetromino currentPiece;

    public Game() {

        this.board = new Block[10][24];
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
                for (int j = 0; j < board.length; j++) {
                    board[j][i] = null;
                }
                for (int k = 0; k < board.length; k++) {
                    for (int l = i; l < board[0].length; l++) {
                        if (l < board[0].length - 1) {
                            board[k][l] = board[k][l + 1];
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

    public void checkCollision () {
        for (int i = 0; i < currentPiece.brick.length; i++) {
            for (int j = 0; j < currentPiece.brick[0].length; j++) {
                if (currentPiece.brick[i][j] != 0 && board[currentPiece.x + i][currentPiece.y + j] != null) {

                }
            }
        }
    }

    public void placePiece () {

    }
}
