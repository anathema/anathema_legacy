package net.sf.anathema.platform.tree.display.transform;

public interface TransformOperation {
  void accept(TransformOperationVisitor visitor);
}