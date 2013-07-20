package net.sf.anathema.platform.tree.view.transform;

import net.sf.anathema.platform.tree.view.draw.TransformOperation;

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
