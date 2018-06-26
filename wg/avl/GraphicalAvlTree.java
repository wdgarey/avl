
package wg.avl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * An AVL tree that can be drawn.
 * @author Wesley Garey
 * @param <K> The key data type.
 * @param <E> The data data type.
 */
public class GraphicalAvlTree<K extends Comparable, E> extends AvlTree<K, E> implements Drawable {
  /**
   * The on-screen location of the node.
   */
  private Point mLoc;
  /**
   * The tree to represent.
   */
  private AvlTree<K, E> mSubj;
  /**
   * The on-screen size of the node.
   */
  private Dimension mSize;
  /**
   * Gets the on-screen location.
   * @return The location.
   */
  public Point getLoc() {
    return this.mLoc;
  }
  /**
   * Gets the on-screen size.
   * @return The size.
   */
  public Dimension getSize() {
    return this.mSize;
  }
  /**
   * Gets the tree being represented.
   * @return The tree.
   */
  public AvlTree<K, E> getSubj() {
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
   * Sets the on-screen size.
   * @param size The size.
   */
  public void setSize(Dimension size) {
    this.mSize = size;
  }
  /**
   * Sets the tree to represent.
   * @param subj The tree.
   */
  public void setSubj(AvlTree<K, E> subj) {
    this.mSubj = subj;
  }
  /**
   * Creates an instance of the GraphicalAvlTree class.
   */
  public GraphicalAvlTree() {
    this.mLoc = new Point();
    this.mSize = new Dimension();
    this.mSubj = new AvlTree<K, E>();
  }
  /**
   * Indicates if this tree contains the given key.
   * @param key The key.
   * @return True, if this tree contains the given key.
   */
  @Override
  public boolean contains(K key) {
    return this.getSubj().contains(key);
  }
  /**
   * Creates a node.
   * @return The new node.
   */
  @Override
  protected AvlTreeNode<K, E> createNode() {
    return this.getSubj().createNode();
  }
  /**
   * Decrements the count.
   */
  @Override
  protected void decrementCount() {
    this.getSubj().decrementCount();
  }
  /**
   * Draws the tree.
   * @param g The graphics to draw with.
   */
  @Override
  public void draw(Graphics g) {
    Point loc = this.getLoc();
    Dimension size = this.getSize();
    if (!this.isEmpty()) {
      GraphicalAvlTreeNode<K, E> root = new GraphicalAvlTreeNode<K, E>();
      root.setSubj(this.getRoot());
      root.setLoc(new Point(loc.x + (int)(size.width / 2.0), loc.y));
      root.setMaxX(loc.x + size.width);
      root.setMinX(loc.x);
      root.setSize(new Dimension(10, 10));
      root.draw(g);
    }
  }
  /**
   * Gets the number of elements stored in the tree.
   * @return The number of elements.
   */
  @Override
  public int getCount() {
    return this.getSubj().getCount();
  }
  /**
   * Gets the collection of elements ordered by key value.
   * @return The ordered collection of elements.
   */
  @Override
  public Object[] getInOrder() {
    return this.getSubj().getInOrder();
  }
  /**
   * The recursive method to get the collection of elements in order.
   * @param node The current node.
   * @param index The current index.
   * @param collection The collection.
   * @return The new index.
   */
  @Override
  protected int getInOrderRec(AvlTreeNode<K, E> node, int index, Object[] collection) {
    return this.getSubj().getInOrderRec(node, index, collection);
  }
  /**
   * Gets the element that is associated with the largest key.
   * @return The element.
   */
  @Override
  public E getMax() {
    return this.getSubj().getMax();
  }
  /**
   * Gets the element that is associated with the smallest key.
   * @return The element.
   */
  @Override
  public E getMin() {
    return this.getSubj().getMin();
  }
  /**
   * Gets the root node.
   * @return The root node.
   */
  @Override
  protected AvlTreeNode<K, E> getRoot() {
    return this.getSubj().getRoot();
  }
  /**
   * Increments the current count by one.
   */
  @Override
  protected void incrementCount() {
    this.getSubj().incrementCount();
  }
  /**
   * Inserts an element into the tree based on the key.
   * @param key The key.
   * @param data The element.
   */
  @Override
  public void insert(K key, E data) {
    this.getSubj().insert(key, data);
  }
  /**
   * Recursively inserts a node.
   * @param key The new key.
   * @param data The new data.
   * @param node The current node.
   * @return The parent.
   */
  @Override
  protected AvlTreeNode<K, E> insertRec(K key, E data, AvlTreeNode<K, E> node) {
    return this.getSubj().insertRec(key, data, node);
  }
  /**
   * Indicates if the tree is empty or not.
   * @return True, if the tree is empty; otherwise, false.
   */
  @Override
  public boolean isEmpty() {
    return this.getSubj().isEmpty();
  }
  /**
   * Removes an element from the tree based on it's key.
   * @param key The key.
   */
  @Override
  public void remove(K key) {
    this.getSubj().remove(key);
  }
  /**
   * Recursively removes a node.
   * @param key The key to search for.
   * @param node The current node.
   * @return The parent.
   */
  @Override
  protected AvlTreeNode<K, E> removeRec(K key, AvlTreeNode<K, E> node) {
    return super.removeRec(key, node); //To change body of generated methods, choose Tools | Templates.
  }
  /**
   * Sets the current count.
   * @param count The current count.
   */
  @Override
  protected void setCount(int count) {
    this.getSubj().setCount(count);
  }
  /**
   * Sets the root node.
   * @param root The root node.
   */
  @Override
  protected void setRoot(AvlTreeNode<K, E> root) {
    this.getSubj().setRoot(root);
  }
  /**
   * Gets the string representation of the tree.
   * @return The string representation.
   */
  @Override
  public String toString() {
    return this.getSubj().toString();
  }
}
