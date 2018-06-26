
package wg.avl;

import javax.swing.JFrame;

/**
 *
 * @author Wesley Garey
 */
public class EntryPoint {
  public static void main(String[] args) {
    GraphicalAvlFrame mainFrame = new GraphicalAvlFrame();
    mainFrame.initialize();
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setVisible(true);
  }
}
