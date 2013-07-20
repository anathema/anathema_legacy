package net.sf.anathema.platform.tree.view.transform;

import net.sf.anathema.platform.tree.display.transform.CenterOn;
import net.sf.anathema.platform.tree.display.transform.PreConcatenate;
import net.sf.anathema.platform.tree.display.transform.Rotation;
import net.sf.anathema.platform.tree.display.transform.Scale;
import net.sf.anathema.platform.tree.display.transform.TransformOperationVisitor;
import net.sf.anathema.platform.tree.display.transform.Translation;

import java.awt.geom.AffineTransform;

public class SwingVisitor implements TransformOperationVisitor {
  private AffineTransform affineTransform;

  public SwingVisitor(AffineTransform affineTransform) {
    this.affineTransform = affineTransform;
  }

  @Override
  public void visitPreConcatenate(PreConcatenate preConcatenate) {
    AffineTransform matrixToPreconcatenate = SwingTransformer.convert(preConcatenate.scaleInstance);
    affineTransform.preConcatenate(matrixToPreconcatenate);
  }

  @Override
  public void visitTranslation(Translation translation) {
    affineTransform.translate(translation.x, translation.y);
  }

  @Override
  public void visitCenterOn(CenterOn centerOn) {
    AffineTransform newCenter = AffineTransform.getTranslateInstance(centerOn.newCenterX, centerOn.newCenterY);
    affineTransform.preConcatenate(newCenter);
  }

  @Override
  public void visitScale(Scale scale) {
    affineTransform.scale(scale.scale, scale.scale);
  }

  @Override
  public void visitRotation(Rotation rotation) {
    affineTransform.rotate(rotation.angle);
  }
}