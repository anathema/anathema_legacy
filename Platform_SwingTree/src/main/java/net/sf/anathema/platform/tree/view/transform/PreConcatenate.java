package net.sf.anathema.platform.tree.view.transform;

import net.sf.anathema.platform.tree.view.draw.TransformOperation;

public class PreConcatenate implements TransformOperation {
  public AgnosticTransform scaleInstance;

  public PreConcatenate(AgnosticTransform scaleInstance) {
    this.scaleInstance = scaleInstance;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitPreConcatenate(this);
  }
}
