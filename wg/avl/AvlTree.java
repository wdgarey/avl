
package wg.avl;

/**
 * An AVL tree.
 * @author Wesley Garey
 * @param <K> The key data type.
 * @param <E> The data data type.
 */
public class AvlTree<K extends Comparable, E> {
  /**
   * The number of elements in the tree.
   */
  private int mCount;
  /**
   * The root node.
   */
  private AvlTreeNode<K, E> mRoot;
  /**
   * Gets the number of elements in the tree.
   * @return The number.
   */
  public int getCount() {
    return this.mCount;
  }
  /**
   * Gets the root node.
   * @return The root node.
   */
  protected AvlTreeNode<K, E> getRoot() {
    return this.mRoot;
  }
  /**
   * Sets the number of elements in the tree.
   * @param count The number.
   */
  protected void setCount(int count) {
    this.mCount = count;
  }
  /**
   * Sets the root node.
   * @param root The root node.
   */
  protected void setRoot(AvlTreeNode<K, E> root) {
    this.mRoot = root;
  }
  /**
   * Creates an instance of the AvlTree class.
   */
  public void AvlTree() {
    this.mCount = 0;
    this.mRoot = null;
  }
  /**
   * Indicates if this tree contains a key.
   * @param key The key to search for.
   * @return True, if this tree contains the key.
   */
  public boolean contains(K key) {
    boolean contains = false;
    AvlTreeNode<K, E> temp = this.getRoot();
    while (temp != null && contains == false) {
      K tempKey = temp.getKey();
      if (tempKey.compareTo(key) < 0) {
        temp = temp.getRight();
      } else if (tempKey.compareTo(key) > 0) {
        temp = temp.getLeft();
      } else {
        contains = true;
      }
    }
    return contains;
  }
  /**
   * Creates a new node.
   * @return The new node.
   */
  protected AvlTreeNode<K, E> createNode() {
     return new AvlTreeNode<K, E>();
  }
  /**
   * Decreases the element count by one.
   */
  protected void decrementCount() {
    this.setCount(this.getCount() - 1);
  }
  /**
   * Gets the collection of elements in order.
   * @return The collection.
   */
  public Object[] getInOrder() {
    int index = 0;
    int count = this.getCount();
    AvlTreeNode<K, E> root = this.getRoot();
    Object[] collection = new Object[count];
    this.getInOrderRec(root, index, collection);
    return collection;
  }
  /**
   * Recursively gets each element in the tree.
   * @param node The current node.
   * @param index The current index.
   * @param collection The collection.
   * @return The new index value.
   */
  protected int getInOrderRec(AvlTreeNode<K, E> node, int index, Object[] collection) {
    if (node != null) {
      index = this.getInOrderRec(node.getLeft(), index, collection);
      collection[index++] = node.getData();
      index = this.getInOrderRec(node.getRight(), index, collection);
    }
    return index;
  }
  /**
   * Gets the smallest element
   * @return The smallest element.
   */
  public E getMin() {
    E min = null;
    AvlTreeNode<K, E> temp = this.getRoot();
    if (!this.isEmpty()) {
      while (temp.getLeft() != null) {
        temp = temp.getLeft();
      }
      min = temp.getData();
    }
    return min;
  }
  /**
   * The largest element.
   * @return The element.
   */
  public E getMax() {
    E max = null;
    AvlTreeNode<K, E> temp = this.getRoot();
    if (!this.isEmpty()) {
      while (temp.getRight() != null) {
        temp = temp.getRight();
      }
      max = temp.getData();
    }
    return max;
  }
  /**
   * Increases the element count by one.
   */
  protected void incrementCount() {
    this.setCount(this.getCount() + 1);
  }
  /**
   * Indicates if the tree is empty.
   * @return True, if the tree is empty.
   */
  public boolean isEmpty() {
    int count = this.getCount();
    boolean isEmpty = (count == 0);
    return isEmpty;
  }
  /**
   * Inserts an element into the tree.
   * @param key The key of the element.
   * @param data The element.
   */
  public void insert(K key, E data) {
    AvlTreeNode<K, E> root = this.getRoot();
    this.setRoot(this.insertRec(key, data, root));
  }
  /**
   * Recursively inserts a new node.
   * @param key The key to insert.
   * @param data The data to insert.
   * @param node The current node.
   * @return The current node.
   */
  protected AvlTreeNode<K, E> insertRec(K key, E data, AvlTreeNode<K, E> node) {
    if (node == null) {
      node = this.createNode();
      node.setKey(key);
      node.setData(data);
      this.incrementCount();
    } else if (key.compareTo(node.getKey()) < 0) {
      node.setLeft(this.insertRec(key, data, node.getLeft()));
      node.getLeft().setParent(node);
    } else {
      node.setRight(this.insertRec(key, data, node.getRight()));
      node.getRight().setParent(node);
    }
    node.updateHeight();
    node = node.rebalance();
    return node;
  }
  /**
   * Inserts an element into the tree.
   * @param key The key of the element.
   */
  public void remove(K key) {
    AvlTreeNode<K, E> root = this.getRoot();
    this.setRoot(this.removeRec(key, root));
  }
  /**
   * Recursively removes a node.
   * @param key The key to remove.
   * @param node The current node.
   * @return The current node.
   */
  protected AvlTreeNode<K, E> removeRec(K key, AvlTreeNode<K, E> node) {
    if (node != null) {
      if (key.compareTo(node.getKey()) < 0) {
        node.setLeft(this.removeRec(key, node.getLeft()));
        node.updateHeight();
      } else if (key.compareTo(node.getKey()) > 0) {
        node.setRight(this.removeRec(key, node.getRight()));
        node.updateHeight();
      } else if (node.hasBoth()) {
        AvlTreeNode<K, E> predecessor = node.getPredecessor();
        node.setData(predecessor.getData());
        node.setKey(predecessor.getKey());
        node.setLeft(this.removeRec(predecessor.getKey(), node.getLeft()));
        node.updateHeight();
      } else if (node.hasLeft()) {
        AvlTreeNode<K, E> left = node.getLeft();
        left.setParent(node.getParent());
        if (node.hasParent()) {
          AvlTreeNode<K, E> parent = node.getParent();
          if (node.isLeft()) {
            parent.setLeft(left);
          } else {
            parent.setRight(left);
          }
        }
        node.setLeft(null);
        node.setParent(null);
        node = left;
        this.decrementCount();
      } else if (node.hasRight()) {
        AvlTreeNode<K, E> right = node.getRight();
        right.setParent(node.getParent());
        if (node.hasParent()) {
          AvlTreeNode<K, E> parent = node.getParent();
          if (node.isLeft()) {
            parent.setLeft(right);
          } else {
            parent.setRight(right);
          }
        }
        node.setRight(null);
        node.setParent(null);
        node = right;
        this.decrementCount();
      } else {
        if (node.hasParent()) {
          AvlTreeNode<K, E> parent = node.getParent();
          if (node.isLeft()) {
            parent.setLeft(null);
          } else {
            parent.setRight(null);
          }
          node.setParent(null);
          node = null;
        }
        this.decrementCount();
      }
      if (node != null) {
        node = node.rebalance();
      }
    }
    return node;
  }
  /**
   * Gets the string representation of the tree.
   * @return The string representation.
   */
  @Override
  public String toString() {
    String rep = "(empty)";
    AvlTreeNode<K, E> root = this.getRoot();
    if (!this.isEmpty()) {
      rep = root.toString();
    }
    return rep;
  }
}
