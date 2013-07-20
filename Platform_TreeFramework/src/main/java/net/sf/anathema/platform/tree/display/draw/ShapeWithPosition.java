package net.sf.anathema.platform.tree.display.draw;

import net.sf.anathema.platform.tree.display.shape.AgnosticShape;

public interface ShapeWithPosition {

  void placeBelow(AgnosticShape shape);
}