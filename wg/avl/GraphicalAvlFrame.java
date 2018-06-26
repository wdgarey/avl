
package wg.avl;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * The GOL frame.
 * @author Wesley Garey
 */
public class GraphicalAvlFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
  /**
   * The start/stop button.
   */
  private JButton mAddBtn;
  /**
   * The drawing canvas.
   */
  private DrawPanel mCanvas;
  /**
   * The start/stop button.
   */
  private JButton mRmBtn;
  /**
   * The text field.
   */
  private JTextField mNumTxt;
  /**
   * The tree.
   */
  private GraphicalAvlTree mTree;
  /**
   * The timer used to update.
   */
  private Timer mUpdateTmr;
  /**
   * Gets the add button.
   * @return The button.
   */
  protected JButton getAddBtn() {
    return this.mAddBtn;
  }
  /**
   * Gets the drawing canvas.
   * @return The drawing canvas.
   */
  protected DrawPanel getCanvas() {
    return this.mCanvas;
  }
  /**
   * Gets the text field.
   * @return The text field.
   */
  protected JTextField getNumTxt() {
    return this.mNumTxt;
  }
  /**
   * Gets the start/stop button.
   * @return The start/stop button.
   */
  protected JButton getRmBtn() {
    return this.mRmBtn;
  }
  /**
   * Gets the tree.
   * @return The tree.
   */
  protected GraphicalAvlTree getTree() {
    return this.mTree;
  }
  /**
   * Gets the timer used to update.
   * @return The timer.
   */
  protected Timer getUpdateTmr() {
    return this.mUpdateTmr;
  }
  /**
   * Sets the add button.
   * @param addBtn The button.
   */
  protected void setAddBtn(JButton addBtn) {
    this.mAddBtn = addBtn;
  }
  /**
   * Sets the drawing canvas.
   * @param canvas The drawing canvas.
   */
  protected void setCanvas(DrawPanel canvas) {
    this.mCanvas = canvas;
  }
  /**
   * Sets the text field.
   * @param rmTxt The text field.
   */
  protected void setNumTxt(JTextField rmTxt) {
    this.mNumTxt = rmTxt;
  }
  /**
   * Sets the start/stop button.
   * @param startStopBtn The start/stop button.
   */
  protected void setRmBtn(JButton startStopBtn) {
    this.mRmBtn = startStopBtn;
  }
  /**
   * Sets the tree.
   * @param tree The tree.
   */
  protected void setTree(GraphicalAvlTree tree) {
    this.mTree = tree;
  }
  /**
   * Sets the timer used to update.
   * @param updateTmr The timer.
   */
  protected void setUpdateTmr(Timer updateTmr) {
    this.mUpdateTmr = updateTmr;
  }
  /**
   * Creates an instance of the BasicGolFrame class.
   */
  public GraphicalAvlFrame() {
    super();
    this.mAddBtn = null;
    this.mCanvas = null;
    this.mNumTxt = null;
    this.mRmBtn = null;
    this.mTree = null;
    this.mUpdateTmr = null;
  }
  /**
   * Notifies the frame that an action was performed.
   * @param e The action event.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == this.getUpdateTmr()) {
      this.updateCb();
    } else if (src == this.getAddBtn()) {
      int num = Integer.parseInt(this.getNumTxt().getText());
      GraphicalAvlTree tree = this.getTree();
      tree.insert(num, num);
      this.updateCb();
    } else if (src == this.getRmBtn()) {
      GraphicalAvlTree tree = this.getTree();
      tree.remove(Integer.parseInt(this.getNumTxt().getText()));
      this.updateCb();
    }
  }
  /**
   * Initializes the frame.
   */
  public void initialize() {
    //Set up the form
    Container pane = this.getContentPane();
    DrawPanel canvas = new DrawPanel(); //Create the custom canvas for drawing cells.
    Timer updateTmr = new Timer(100, this);
    JButton rmBtn = new JButton("Remove");
    JButton addBtn = new JButton("Add");
    JTextField rmTxt = new JTextField();
    JTextField addTxt = new JTextField();
    rmTxt.setText("Number");
    addTxt.setText("Number");
    GridBagConstraints layoutConstraints = new GridBagConstraints();
    pane.setLayout(new GridBagLayout());
    //Canvas
    canvas.addMouseListener(this);
    canvas.addMouseMotionListener(this);
    canvas.setBgColor(Color.WHITE);
    canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    layoutConstraints.fill = GridBagConstraints.BOTH;
    layoutConstraints.weightx = 1.0;
    layoutConstraints.weighty = 1.0;
    layoutConstraints.gridx = 0;
    layoutConstraints.gridy = 0;
    layoutConstraints.gridwidth = 64;
    layoutConstraints.gridheight = 63;
    pane.add(canvas, layoutConstraints);
    canvas.repaint();
    this.setCanvas(canvas);
    //Start/Stop button
    rmBtn.addActionListener(this);
    layoutConstraints.weightx = 1.0;
    layoutConstraints.weighty = 0.0;
    layoutConstraints.fill = GridBagConstraints.NONE;
    layoutConstraints.anchor = GridBagConstraints.LAST_LINE_END;
    layoutConstraints.gridx = 63;
    layoutConstraints.gridy = 63;
    layoutConstraints.gridwidth = 1;
    layoutConstraints.gridheight = 1;
    pane.add(rmBtn, layoutConstraints);
    this.setRmBtn(rmBtn);
    //Text field.
    rmTxt.addActionListener(this);
    layoutConstraints.weightx = 1.0;
    layoutConstraints.weighty = 0.0;
    layoutConstraints.fill = GridBagConstraints.NONE;
    layoutConstraints.anchor = GridBagConstraints.LAST_LINE_END;
    layoutConstraints.gridx = 62;
    layoutConstraints.gridy = 63;
    layoutConstraints.gridwidth = 1;
    layoutConstraints.gridheight = 1;
    pane.add(rmTxt, layoutConstraints);
    this.setNumTxt(rmTxt);
    //Start/Stop button
    addBtn.addActionListener(this);
    layoutConstraints.weightx = 0.0;
    layoutConstraints.weighty = 0.0;
    layoutConstraints.fill = GridBagConstraints.NONE;
    layoutConstraints.anchor = GridBagConstraints.LAST_LINE_END;
    layoutConstraints.gridx = 61;
    layoutConstraints.gridy = 63;
    layoutConstraints.gridwidth = 1;
    layoutConstraints.gridheight = 1;
    pane.add(addBtn, layoutConstraints);
    this.setAddBtn(addBtn);
    //Update timer
    updateTmr.setDelay(1000); //Set repeat delay.
    updateTmr.setInitialDelay(500); //Set initital delay.
    updateTmr.setRepeats(true); //Make the timer restart on expiration.
    this.setUpdateTmr(updateTmr);
    //Set form values.
    GraphicalAvlTree<Integer, Integer> tree = new GraphicalAvlTree<Integer, Integer>();
    tree.setLoc(new Point (0, 0));
    tree.setSize(new Dimension(1500, 1000));
    canvas.addDrawable(tree);
    this.setTree(tree);
    this.setTitle("Game of Life");
    this.setSize(1600, 1000);
    //Do one initial draw so that the canvas is not blank.
    this.updateCb();
    updateTmr.start();
  }
  /**
   * Indicates if the frame is running.
   * @return True, if the frame is running.
   */
  protected boolean isRunning() {
    Timer updateTmr = this.getUpdateTmr();
    boolean isRunning = updateTmr.isRunning();    
    return isRunning;
  }
  /**
   * Starts the frame.
   */
  protected void start() {
    Timer updateTmr = this.getUpdateTmr();
    updateTmr.start();
  }
  /**
   * Stops the frame.
   */
  protected void stop() {
    Timer updateTmr = this.getUpdateTmr();
    updateTmr.stop();
  }
  /**
   * The update callback.
   */
  protected void updateCb() {
    Random rnd = new Random();
    GraphicalAvlTree tree = this.getTree();
    int count = rnd.nextInt(1000);
    tree.insert(count, count);
    DrawPanel canvas = this.getCanvas();
    canvas.repaint();
  }
  /**
   * Mouse clicked event.
   * @param e The event information.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    Point cellPt = new Point();
    Point clickPt = e.getPoint();
    DrawPanel canvas = this.getCanvas();
    canvas.repaint();
  }
  /**
   * Mouse pressed event.
   * @param e The event information.
   */
  @Override
  public void mousePressed(MouseEvent e) {
  }
  /**
   * Mouse released event.
   * @param e The event information.
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    //Do nothing.
  }
  /**
   * Mouse entered event.
   * @param e The event information.
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    //Do nothing.
  }
  /**
   * Mouse exited event.
   * @param e The event information.
   */
  @Override
  public void mouseExited(MouseEvent e) {
    //Do nothing.
  }
  /**
   * The mouse dragged event.
   * @param e The event information.
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    this.mouseClicked(e);
  }
  /**
   * The mouse moved event.
   * @param e The event information.
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    //Do nothing.
  }
}
