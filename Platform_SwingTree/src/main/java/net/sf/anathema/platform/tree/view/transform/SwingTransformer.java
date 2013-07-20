package net.sf.anathema.platform.tree.view.transform;

import net.sf.anathema.platform.tree.view.draw.TransformOperation;

import java.awt.geom.AffineTransform;

public class SwingTransformer {
  public static AffineTransform convert(AgnosticTransform transform) {
    final AffineTransform affineTransform = new AffineTransform();
    SwingVisitor visitor = new SwingVisitor(affineTransform);
    for (TransformOperation operation : transform) {
      operation.accept(visitor);
    }
    return affineTransform;
  }
}