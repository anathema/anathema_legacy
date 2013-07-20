package net.sf.anathema.platform.tree.display.transform;

public class Scale implements TransformOperation {
  public double scale;

  public Scale(double scale) {
    this.scale = scale;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitScale(this);
  }
}