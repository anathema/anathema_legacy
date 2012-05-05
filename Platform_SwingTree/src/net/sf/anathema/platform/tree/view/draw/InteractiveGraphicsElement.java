package net.sf.anathema.platform.tree.view.draw;

import java.awt.geom.Point2D;

public interface InteractiveGraphicsElement extends GraphicsElement {
  boolean contains(Point2D p);

  void toggle();
}
