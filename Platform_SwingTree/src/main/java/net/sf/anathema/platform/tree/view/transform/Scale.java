package net.sf.anathema.platform.tree.view.transform;

import net.sf.anathema.platform.tree.view.draw.TransformOperation;

public class Scale implements TransformOperation {
  public double scale;

  public Scale(double scale) {
    this.scale = scale;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitScale(this);
  }
}
