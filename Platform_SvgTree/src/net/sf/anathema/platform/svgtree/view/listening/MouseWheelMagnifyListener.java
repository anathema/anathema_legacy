package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.swing.gvt.JGVTComponent;

public class MouseWheelMagnifyListener implements MouseWheelListener {

  private final IBoundsCalculator calculator;
  
  private static final float MAX_ZOOM_DETERMINANT = .65f;

  public MouseWheelMagnifyListener(IBoundsCalculator boundsCalculator) {
    this.calculator = boundsCalculator;
  }
  
  

  public void mouseWheelMoved(MouseWheelEvent event) {
    JGVTComponent component = (JGVTComponent) event.getSource();
    double scale = 1 - 0.2 * event.getWheelRotation();
    int x = event.getX();
    int y = event.getY();
    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
    at.scale(scale, scale);
    at.translate(-x, -y);
    AffineTransform rt = (AffineTransform) component.getRenderingTransform().clone();
    rt.preConcatenate(at);
    if (rt.getDeterminant() < MAX_ZOOM_DETERMINANT) {
      return;
    }
    component.setRenderingTransform(rt);
    calculator.reset();
  }
}