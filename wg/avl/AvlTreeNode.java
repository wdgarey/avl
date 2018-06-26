
package wg.avl;

/**
 *
 * @author Wesley Garey
 * @param <K> The key type
 * @param <E> The data type 
 */
public class AvlTreeNode<K, E> {
  /**
   * The data.
   */
  private E mData;
  /**
   * The height.
   */
  private int mHeight;
  /**
   * The key.
   */
  private K mKey;
  /**
   * The left child.
   */
  private AvlTreeNode<K, E> mLeft;
  /**
   * The parent.
   */
  private AvlTreeNode<K, E> mParent;
  /**
   * The right child.
   */
  private AvlTreeNode<K, E> mRight;
  /**
   * Gets the data.
   * @return The data.
   */
  public E getData() {
    return this.mData;
  }
  /**
   * Gets the height.
   * @return The height.
   */
  public int getHeight() {
    return this.mHeight;
  }
  /**
   * Gets the key.
   * @return The key.
   */
  public K getKey() {
    return this.mKey;
  }
  /**
   * Gets the left child.
   * @return The left child.
   */
  public AvlTreeNode<K, E> getLeft() {
    return this.mLeft;
  }
  /**
   * Gets the parent.
   * @return The parent.
   */
  public AvlTreeNode<K, E> getParent() {
    return this.mParent;
  }
  /**
   * Gets the right child.
   * @return The right child.
   */
  public AvlTreeNode<K, E> getRight() {
    return this.mRight;
  }
  /**
   * Sets the data.
   * @param data The data.
   */
  public void setData(E data) {
    this.mData = data;
  }
  /**
   * Sets the height.
   * @param height The height.
   */
  public void setHeight(int height) {
    this.mHeight = height;
  }
  /**
   * Sets the key.
   * @param key The key.
   */
  public void setKey(K key) {
    this.mKey = key;
  }
  /**
   * Sets the left child.
   * @param left The left child.
   */
  public void setLeft(AvlTreeNode<K, E> left) {
    this.mLeft = left;
  }
  /**
   * Sets the parent.
   * @param parent The parent.
   */
  public void setParent(AvlTreeNode<K, E> parent) {
    this.mParent = parent;
  }
  /**
   * Sets the right child.
   * @param right The right child.
   */
  public void setRight(AvlTreeNode<K, E> right) {
    this.mRight = right;
  }
  /**
   * Creates an instance of the Tree node class.
   */
  public AvlTreeNode() {
    this.mData = null;
    this.mHeight = -1;
    this.mKey = null;
    this.mLeft = null;
    this.mParent = null;
    this.mRight = null;
  }
  /**
   * Gets the balance factor of the node.
   * @return The balance factor.
   */
  public int getBalFac() {
    int leftHeight = this.getLeftHeight();
    int rightHeight = this.getRightHeight();
    int balFac = (leftHeight - rightHeight);
    return balFac;
  }
  /**
   * Gets the height of the left tree.
   * @return The height.
   */
  public int getLeftHeight() {
    int height = -1;
    if (this.hasLeft()) {
      height = this.getLeft().getHeight();
    }
    return height;
  }
  /**
   * Gets the predecessor.
   * @return The predecessor.
   */
  public AvlTreeNode<K, E> getPredecessor() {
    AvlTreeNode<K, E> predecessor = this.getLeft();
    if (predecessor != null) {
      while (predecessor.getRight() != null) {
        predecessor = predecessor.getRight();
      }
    }
    return predecessor;
  }
  /**
   * Gets the height of the right tree.
   * @return The height.
   */
  public int getRightHeight() {
    int height = -1;
    if (this.hasRight()) {
      height = this.getRight().getHeight();
    }
    return height;
  }
  /**
   * Indicates if this node has both children.
   * @return True, if this node has both children.
   */
  public boolean hasBoth() {
    boolean hasBoth = (this.hasLeft() && this.hasRight());
    return hasBoth;
  }
  /**
   * Indicates if this node has a left child.
   * @return True, if this node has a left child.
   */
  public boolean hasLeft() {
    boolean hasLeft = (this.getLeft() != null);
    return hasLeft;
  }
  /**
   * Indicates if this node has a parent.
   * @return True, if this node has a parent; otherwise, false.
   */
  public boolean hasParent() {
    boolean hasParent = (this.getParent() != null);
    return hasParent;
  }
  /**
   * Indicates if this node has a right child.
   * @return True, if this node has a right child.
   */
  public boolean hasRight() {
    boolean hasRight = (this.getRight() != null);
    return hasRight;
  }
  /**
   * Indicates if this node is a left child node.
   * @return True, if this node is a left child node.
   */
  public boolean isLeft() {
    boolean isLeft = (this.hasParent() && this.getParent().getLeft() == this);
    return isLeft;
  }
  /**
   * Indicates if this node is left heavy.
   * @return True, if this node is left heavy.
   */
  public boolean isLeftHeavy() {
    int balFac = this.getBalFac();
    boolean isLeftHeavy = (balFac > 1);
    return isLeftHeavy;
  }
  /**
   * Indicates if this node is a right child node.
   * @return True, if this node is a right child node.
   */
  public boolean isRight() {
    boolean isRight = (this.hasParent() && this.getParent().getRight() == this);
    return isRight;
  }
  /**
   * Indicates if this node is right heavy.
   * @return True, if this node is right heavy.
   */
  public boolean isRightHeavy() {
    int balFac = this.getBalFac();
    boolean isRightHeavy = (balFac < -1);
    return isRightHeavy;
  }
  /**
   * Re-balances the sub-tree.
   * @return The new root of the sub-tree.
   */
  public AvlTreeNode<K, E> rebalance() {
    AvlTreeNode<K, E> root = this;
    if (this.isLeftHeavy()) {
      if (this.getLeft().getLeftHeight() < this.getLeft().getRightHeight()) {
        this.getLeft().rotateLeft();
      }
      this.rotateRight();
      root = this.getParent();
    } else if (this.isRightHeavy()) {
      if (this.getRight().getRightHeight() < this.getRight().getLeftHeight()) {
        this.getRight().rotateRight();
      }
      this.rotateLeft();
      root = this.getParent();
    }
    root.updateHeight();
    return root;
  }
  /**
   * Rotates this node to the left.
   */
  public void rotateLeft() {
    AvlTreeNode<K, E> right = this.getRight();
    this.setRight(right.getLeft());
    right.setLeft(this);
    if (this.hasRight()) {
      this.getRight().setParent(this);
    }
    if (this.isRight()) {
      this.getParent().setRight(right);
    } else if (this.isLeft()) {
      this.getParent().setLeft(right);
    }
    right.setParent(this.getParent());
    this.setParent(right);
    this.updateHeight();
    right.updateHeight();
  }
  /**
   * Rotates this node to the right.
   */
  public void rotateRight() {
    AvlTreeNode<K, E> left = this.getLeft();
    this.setLeft(left.getRight());
    left.setRight(this);
    if (this.hasLeft()) {
      this.getLeft().setParent(this);
    }
    if (this.isRight()) {
      this.getParent().setRight(left);
    } else if (this.isLeft()) {
      this.getParent().setLeft (left);
    }
    left.setParent(this.getParent());
    this.setParent(left);
    this.updateHeight();
    left.updateHeight();
  }
  /**
   * Gets the string representation of this instance.
   * @return The string representation.
   */
  @Override
  public String toString() {
    K key = this.getKey();
    E data = this.getData();
    int height = this.getHeight();
    String rep = "(key=" + key + ";data=" + data + ";height=" + height + ")";
    return rep;
  }
  /**
   * Updates the height of this node.
   */
  public void updateHeight() {
    int leftHeight = this.getLeftHeight();
    int rightHeight = this.getRightHeight();
    int height = Math.max(leftHeight, rightHeight) + 1;
    this.setHeight(height);
  }
}
