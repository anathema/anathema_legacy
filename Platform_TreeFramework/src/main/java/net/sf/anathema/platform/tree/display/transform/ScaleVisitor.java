package net.sf.anathema.platform.tree.display.transform;

public class ScaleVisitor implements TransformOperationVisitor {
  private double scale = 1.0;

  @Override
  public void visitPreConcatenate(PreConcatenate preConcatenate) {
    preConcatenate.scaleInstance.visitOperations(this);
  }

  @Override
  public void visitTranslation(Translation translation) {
    //nothing to do
  }

  @Override
  public void visitCenterOn(CenterOn centerOn) {
    //nothing to do
  }

  @Override
  public void visitScale(Scale operation) {
    scale = scale * operation.scale;
  }

  @Override
  public void visitRotation(Rotation rotation) {
    //nothing to do
  }

  public double getScale() {
    return scale;
  }
}
