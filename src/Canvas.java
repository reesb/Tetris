import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Rees on 11/7/14.
 */
public class Canvas extends JPanel implements ActionListener{

    private int timeStamp = 0;
    private int speed = 50;
    private Timer timer;
    private Game game;

    public Canvas(int x, int y) {

        addMouseMotionListener(new MouseMotionInput());
        addMouseListener(new MouseInput());
        addKeyListener(new KeyInput());

        setFocusable(true);
        setDoubleBuffered(true);

        game = new Game();

        timer = new Timer(20, this);
        timer.start();

    }

    public void paint(Graphics g) {
        super.paint(g);
        game.draw(g);
    }

    private class MouseMotionInput implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    private class MouseInput implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class KeyInput implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

            if (e.getExtendedKeyCode() == KeyEvent.VK_A) {
                game.movePieceLeft();
            }
            if (e.getExtendedKeyCode() == KeyEvent.VK_D) {
                game.movePieceRight();
            }
            if (e.getExtendedKeyCode() == KeyEvent.VK_E) {
                game.rotatePieceCW();
            }
            if (e.getExtendedKeyCode() == KeyEvent.VK_Q) {
                game.rotatePieceCCW();
            }
            if (e.getExtendedKeyCode() == KeyEvent.VK_R) {
                game.reset();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getExtendedKeyCode() == KeyEvent.VK_S) {
                game.hardDrop();
            }
            if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
                game.tick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeStamp++;
        if (timeStamp >= speed) {
            game.tick();
            timeStamp = 0;
        }
        repaint();
    }
}