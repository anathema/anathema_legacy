package net.sf.anathema.platform.tree.display.transform;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Rotation implements TransformOperation {
  public double angle;

  public Rotation(double angle) {
    this.angle = angle;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitRotation(this);
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return (int) angle;
  }
}