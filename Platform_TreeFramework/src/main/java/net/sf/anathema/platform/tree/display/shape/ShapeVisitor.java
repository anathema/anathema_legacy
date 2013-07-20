package net.sf.anathema.platform.tree.display.shape;

public interface ShapeVisitor {
  void visitPolygon(Polygon polygon);

  void visitCircle(Circle circle);
}
