package net.sf.anathema.platform.tree.display.shape;

import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class TransformedShape {

  public final AgnosticShape shape;
  public final AgnosticTransform transform;

  public TransformedShape(AgnosticShape shape, AgnosticTransform transform) {
    this.shape = shape;
    this.transform = transform;
  }

  public TransformedShape(AgnosticShape shape) {
    this(shape, new AgnosticTransform());
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return shape.hashCode() * transform.hashCode();
  }
}