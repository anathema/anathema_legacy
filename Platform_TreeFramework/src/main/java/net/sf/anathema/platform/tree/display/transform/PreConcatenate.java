package net.sf.anathema.platform.tree.display.transform;

public class PreConcatenate implements TransformOperation {
  public AgnosticTransform scaleInstance;

  public PreConcatenate(AgnosticTransform scaleInstance) {
    this.scaleInstance = scaleInstance;
  }

  public void accept(TransformOperationVisitor visitor) {
    visitor.visitPreConcatenate(this);
  }
}