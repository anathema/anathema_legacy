package net.sf.anathema.platform.tree.display.transform;

public interface TransformOperationVisitor {

  void visitTranslation(Translation translation);

  void visitScale(Scale scale);

  void visitRotation(Rotation rotation);
}