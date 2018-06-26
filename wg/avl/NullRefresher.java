
package wg.avl;

/**
 *
 * @author Wesley Garey
 */
public class NullRefresher implements Refresher {
  /**
   * The static instance of the NullRefresher class.
   */
  private static NullRefresher sInstance;
  /**
   * Gets the static instance of the NullRefresher class.
   * @return The static instance.
   */
  public static NullRefresher getInstance() {
    if (NullRefresher.sInstance == null) {
      NullRefresher.sInstance = new NullRefresher();
    }
    return NullRefresher.sInstance;
  }
  /**
   * Creates an instance of the NullRefresher class.
   */
  protected NullRefresher() {
    //Do nothing.
  }
  /**
   * Refresh.
   */
  @Override
  public void refresh() {
    //Do nothing.
  }
}
