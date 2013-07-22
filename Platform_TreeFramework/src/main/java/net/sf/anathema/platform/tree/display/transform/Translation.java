package net.sf.anathema.platform.tree.display.transform;

public class Translation implements TransformOperation {
  public final double x;
  public final double y;

  public Translation(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitTranslation(this);
  }
}