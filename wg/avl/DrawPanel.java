
package wg.avl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JPanel;

/**
 * A class used to draw Drawable objects.
 * @author Wesley Garey
 */
public class DrawPanel extends JPanel {
  /**
   * The background color.
   */
  private Color mBgColor;
  /**
   * The collection of objects to draw.
   */
  private Collection<Drawable> mDrawables;
  /**
   * Gets the background color.
   * @return The color.
   */
  public Color getBgColor() {
    return this.mBgColor;
  }
  /**
   * Gets the collection of objects to draw.
   * @return The collection.
   */
  public Collection<Drawable> getDrawables() {
    return this.mDrawables;
  }
  /**
   * Sets the background color.
   * @param bgColor The color.
   */
  public void setBgColor(Color bgColor) {
    this.mBgColor = bgColor;
  }
  /**
   * Sets the collection of objects to draw.
   * @param drawables The collection of objects to draw.
   */
  public void setDrawables(Collection<Drawable> drawables) {
    this.mDrawables = drawables;
  }
  /**
   * Creates an instance of the DrawPanel class.
   */
  public DrawPanel() {
    this.mBgColor = Color.BLACK;
    this.mDrawables = new ArrayList<Drawable>();
  }
  /**
   * Adds a drawable to the collection.
   * @param drawable The drawable to add.
   */
  public void addDrawable(Drawable drawable) {
    Collection<Drawable> drawables = this.getDrawables();
    drawables.add(drawable);
  }
  /**
   * Removes all drawables from the collection.
   */
  public void clearDrawables() {
    Collection<Drawable> drawables = this.getDrawables();
    drawables.clear();
  }
  /**
   * Paints this instance.
   * @param g The graphics to paint with.
   */
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Dimension size = this.getSize();
    Color bgColor = this.getBgColor();
    g.setColor(bgColor);
    g.fillRect(0, 0, size.width, size.height);
    Collection<Drawable> drawables = this.getDrawables();
    for (Drawable drawable : drawables) {
      drawable.draw(g);
    }
  }
}
