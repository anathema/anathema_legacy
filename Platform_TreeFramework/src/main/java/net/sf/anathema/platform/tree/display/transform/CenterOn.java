package net.sf.anathema.platform.tree.display.transform;

import org.apache.commons.lang3.builder.EqualsBuilder;

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

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return newCenterX + newCenterY;
  }
}