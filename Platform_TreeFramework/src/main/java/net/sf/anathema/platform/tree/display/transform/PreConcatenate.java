package net.sf.anathema.platform.tree.display.transform;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class PreConcatenate implements TransformOperation {
  public AgnosticTransform scaleInstance;

  public PreConcatenate(AgnosticTransform scaleInstance) {
    this.scaleInstance = scaleInstance;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitPreConcatenate(this);
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return scaleInstance.hashCode();
  }
}