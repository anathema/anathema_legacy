package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.platform.tree.view.transform.TransformOperationVisitor;

public interface TransformOperation {
  void accept(TransformOperationVisitor visitor);
}