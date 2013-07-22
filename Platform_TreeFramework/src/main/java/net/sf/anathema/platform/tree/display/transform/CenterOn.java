package net.sf.anathema.platform.tree.display.transform;

public class CenterOn implements TransformOperation {
  public final int newCenterX;
  public final int newCenterY;

  public CenterOn(int newCenterX, int newCenterY) {
    this.newCenterX = newCenterX;
    this.newCenterY = newCenterY;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitCenterOn(this);
  }
}