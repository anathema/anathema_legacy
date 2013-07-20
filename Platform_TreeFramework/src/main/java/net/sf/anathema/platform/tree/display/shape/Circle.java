package net.sf.anathema.platform.tree.display.shape;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Circle implements AgnosticShape {
  public final int centerX;
  public final int centerY;
  public final int diameter;

  public Circle(int centerX, int centerY, int diameter) {
    this.centerX = centerX;
    this.centerY = centerY;
    this.diameter = diameter;
  }

  @Override
  public void accept(ShapeVisitor visitor) {
    visitor.visitCircle(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return centerX * centerY * diameter;
  }
}