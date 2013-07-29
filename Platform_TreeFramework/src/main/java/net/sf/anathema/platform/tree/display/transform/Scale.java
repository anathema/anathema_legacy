package net.sf.anathema.platform.tree.display.transform;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Scale implements TransformOperation {
  public double scale;

  public Scale(double scale) {
    this.scale = scale;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitScale(this);
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return (int) scale;
  }
}