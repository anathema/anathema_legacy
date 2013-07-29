package net.sf.anathema.platform.tree.display.transform;

import org.apache.commons.lang3.builder.EqualsBuilder;

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

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return (int) (x + y);
  }
}