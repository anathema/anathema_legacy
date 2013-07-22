package net.sf.anathema.platform.tree.display.shape;

public interface AgnosticShape {
  void accept(ShapeVisitor visitor);
}
