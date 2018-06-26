
package wg.avl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * An AVL tree node that can be drawn.
 * @author Wesley Garey
 * @param <K> The key type.
 * @param <E> The data type.
 */
public class GraphicalAvlTreeNode<K, E> extends AvlTreeNode<K, E> implements Drawable {
  /**
   * The on-screen location of the node.
   */
  private Point mLoc;
  /**
   * The maximum X value.
   */
  private int mMaxX;
  /**
   * The minimum X value.
   */
  private int mMinX;
  /**
   * The on-screen size of the node.
   */
  private Dimension mSize;
  /**
   * The node to represent.
   */
  private AvlTreeNode<K, E> mSubj;
  /**
   * Gets the on-screen location.
   * @return The location.
   */
  public Point getLoc() {
    return this.mLoc;
  }
  /**
   * Gets the maximum x value.
   * @return The maximum.
   */
  public int getMaxX() {
    return this.mMaxX;
  }
  /**
   * Gets the minimum x value.
   * @return The minimum.
   */
  public int getMinX() {
    return this.mMinX;
  }
  /**
   * Gets the on-screen size.
   * @return The size.
   */
  public Dimension getSize() {
    return this.mSize;
  }
  /**
   * Gets the node being represented.
   * @return The node.
   */
  public AvlTreeNode<K, E> getSubj() {
    return this.mSubj;
  }
  /**
   * Sets the on-screen location.
   * @param loc The location.
   */
  public void setLoc(Point loc) {
    this.mLoc = loc;
  }
  /**
   * Sets the maximum x value.
   * @param maxX The maximum.
   */
  public void setMaxX(int maxX) {
    this.mMaxX = maxX;
  }
  /**
   * The minimum x value.
   * @param minX The minimum.
   */
  public void setMinX(int minX) {
    this.mMinX = minX;
  }
  /**
   * Sets the on-screen size.
   * @param size The size.
   */
  public void setSize(Dimension size) {
    this.mSize = size;
  }
  /**
   * Sets the node to represent.
   * @param subj The node.
   */
  public void setSubj(AvlTreeNode<K, E> subj) {
    this.mSubj = subj;
  }
  /**
   * Creates an instance of the GraphicalAvlTreeNode class.
   */
  public GraphicalAvlTreeNode() {
    this.mLoc = new Point();
    this.mMaxX = 0;
    this.mMinX = 0;
    this.mSize = new Dimension();
    this.mSubj = new AvlTreeNode<K, E>();
  }
  /**
   * Draws the node.
   * @param g The graphics to draw with.
   */
  @Override
  public void draw(Graphics g) {
    int maxX = this.getMaxX();
    int minX = this.getMinX();
    Point loc = this.getLoc();
    Dimension size = this.getSize();
    K key = this.getSubj().getKey();
    g.setColor(Color.BLACK);
    //g.drawOval(loc.x, loc.y, size.width, size.height);
    g.drawString(key.toString(), loc.x + (size.width / 4), loc.y + (size.height));
    if (this.hasLeft()) {
      GraphicalAvlTreeNode<K, E> left = new GraphicalAvlTreeNode<K, E>();
      left.setSubj(this.getLeft());
      left.setLoc(new Point(loc.x - (int)((loc.x - minX) / 2.0), loc.y + size.height + 50));
      left.setMaxX(loc.x);
      left.setMinX(minX);
      left.setSize(new Dimension(size));
      left.draw(g);
      g.drawLine(loc.x + (size.width / 2), loc.y + size.height, left.getLoc().x + (left.getSize().width / 2), left.getLoc().y);
    }
    if (this.hasRight()) {
      GraphicalAvlTreeNode<K, E> right = new GraphicalAvlTreeNode<K, E>();
      right.setSubj(this.getRight());
      right.setLoc(new Point(loc.x + (int)((maxX - loc.x) / 2.0), loc.y + size.height + 50));
      right.setMaxX(maxX);
      right.setMinX(loc.x);
      right.setSize(new Dimension(size));
      right.draw(g);
      g.drawLine(loc.x + (size.width / 2), loc.y + size.height, right.getLoc().x + (right.getSize().width / 2), right.getLoc().y);
    }
  }
  /**
   * Gets the balance factor.
   * @return The balance factor.
   */
  @Override
  public int getBalFac() {
    return this.getSubj().getBalFac();
  }
  /**
   * Gets the data.
   * @return The data.
   */
  @Override
  public E getData() {
    return this.getSubj().getData();
  }
  /**
   * Gets the height.
   * @return The height.
   */
  @Override
  public int getHeight() {
    return this.getSubj().getHeight();
  }
  /**
   * Gets the key.
   * @return The key.
   */
  @Override
  public K getKey() {
    return this.getSubj().getKey();
  }
  /**
   * Gets the left child.
   * @return The left child.
   */
  @Override
  public AvlTreeNode<K, E> getLeft() {
    return this.getSubj().getLeft();
  }
  /**
   * Gets the height of the left node.
   * @return The height.
   */
  @Override
  public int getLeftHeight() {
    return this.getSubj().getLeftHeight();
  }
  /**
   * Gets the parent node.
   * @return 
   */
  @Override
  public AvlTreeNode<K, E> getParent() {
    return this.getSubj().getParent();
  }
  /**
   * Gets the predecessor.
   * @return The predecessor.
   */
  @Override
  public AvlTreeNode<K, E> getPredecessor() {
    return this.getSubj().getPredecessor();
  }
  /**
   * Gets the right child.
   * @return The right child.
   */
  @Override
  public AvlTreeNode<K, E> getRight() {
    return this.getSubj().getRight();
  }
  /**
   * Gets the height of the right child.
   * @return The height.
   */
  @Override
  public int getRightHeight() {
    return this.getSubj().getRightHeight();
  }
  /**
   * Indicates if this nodes has both children.
   * @return True, if this node has both children.
   */
  @Override
  public boolean hasBoth() {
    return this.getSubj().hasBoth();
  }
  /**
   * Indicates if this node has a left child.
   * @return True, if this node has a left child.
   */
  @Override
  public boolean hasLeft() {
    return this.getSubj().hasLeft();
  }
  /**
   * Indicates if this node has a parent.
   * @return True, if this node has a parent.
   */
  @Override
  public boolean hasParent() {
    return this.getSubj().hasParent();
  }
  /**
   * Indicates if this node has a right child.
   * @return True, if this node has a right child.
   */
  @Override
  public boolean hasRight() {
    return this.getSubj().hasRight();
  }
  /**
   * Indicates if this node is left heavy.
   * @return True, if this node is left heavy.
   */
  @Override
  public boolean isLeftHeavy() {
    return this.getSubj().isLeftHeavy();
  }
  /**
   * Indicates if this node is a left child.
   * @return True, if this node is a left child.
   */
  @Override
  public boolean isLeft() {
    return this.getSubj().isLeft();
  }
  /**
   * Indicates if this node is a right child.
   * @return True, if this node is a right child.
   */
  @Override
  public boolean isRight() {
    return this.getSubj().isRight();
  }
  /**
   * Indicates if this node is right heavy.
   * @return True, if this node is right heavy.
   */
  @Override
  public boolean isRightHeavy() {
    return this.getSubj().isRightHeavy();
  }
  /**
   * Re-balances the sub-tree of this node.
   * @return The new root.
   */
  @Override
  public AvlTreeNode<K, E> rebalance() {
    return this.getSubj().rebalance();
  }
  /**
   * Rotates this node to the left.
   */
  @Override
  public void rotateLeft() {
    this.getSubj().rotateLeft();
  }
  /**
   * Rotates this node to the right.
   */
  @Override
  public void rotateRight() {
    this.getSubj().rotateRight();
  }
  /**
   * Sets the data.
   * @param data The data.
   */
  @Override
  public void setData(E data) {
    this.getSubj().setData(data);
  }
  /**
   * Sets the height.
   * @param height The height.
   */
  @Override
  public void setHeight(int height) {
    this.getSubj().setHeight(height);
  }
  /**
   * Sets the key.
   * @param key The key,
   */
  @Override
  public void setKey(K key) {
    this.getSubj().setKey(key);
  }
  /**
   * Sets the left node.
   * @param left The left node.
   */
  @Override
  public void setLeft(AvlTreeNode<K, E> left) {
    this.getSubj().setLeft(left);
  }
  /**
   * Sets the parent of this node.
   * @param parent The parent.
   */
  @Override
  public void setParent(AvlTreeNode<K, E> parent) {
    this.getSubj().setParent(parent);
  }
  /**
   * Sets the right node.
   * @param right The right node.
   */
  @Override
  public void setRight(AvlTreeNode<K, E> right) {
    this.getSubj().setRight(right);
  }
  /**
   * Gets the string representation.
   * @return The string.
   */
  @Override
  public String toString() {
    return this.getSubj().toString();
  }
  /**
   * Updates the height.
   */
  @Override
  public void updateHeight() {
    this.getSubj().updateHeight();
  }
}
