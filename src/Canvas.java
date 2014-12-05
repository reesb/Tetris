import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Rees on 11/7/14.
 */
public class Canvas extends JPanel implements ActionListener{

    private Timer timer;

    public Canvas(int x, int y) {

        addMouseMotionListener(new MouseMotionInput());
        addMouseListener(new MouseInput());
        addKeyListener(new KeyInput());

        setFocusable(true);
        setDoubleBuffered(true);

        timer = new Timer(20, this);
        timer.start();

    }

    public void paint(Graphics g) {
        super.paint(g);

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
            if (timer.isRunning()) {
                timer.stop();
            } else {
                timer.start();
            }
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

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}