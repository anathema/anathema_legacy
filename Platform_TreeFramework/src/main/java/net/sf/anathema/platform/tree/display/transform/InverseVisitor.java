package net.sf.anathema.platform.tree.display.transform;

public class InverseVisitor implements TransformOperationVisitor {
  private AgnosticTransform inverse = new AgnosticTransform();

  @Override
  public void visitTranslation(Translation translation) {
    inverse.preconcatenate(new Translation(-translation.x, -translation.y));
  }

  @Override
  public void visitScale(Scale scale) {
    inverse.preconcatenate(new Scale(1 / scale.scale));
  }

  @Override
  public void visitRotation(Rotation rotation) {
    inverse.preconcatenate(new Rotation(-rotation.angle));
  }

  public AgnosticTransform getInverse() {
    return inverse;
  }
}