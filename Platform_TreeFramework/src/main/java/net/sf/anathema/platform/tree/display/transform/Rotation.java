package net.sf.anathema.platform.tree.display.transform;

public class Rotation implements TransformOperation {
  public double angle;

  public Rotation(double angle) {
    this.angle = angle;
  }
  public void accept(TransformOperationVisitor visitor) {
    visitor.visitRotation(this);
  }
}