package net.sf.anathema.charmtree.listening;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import net.sf.anathema.charmtree.batik.BoundsCalculator;

import org.apache.batik.swing.gvt.JGVTComponent;

public class MouseWheelMagnifyListener implements MouseWheelListener {

  private final BoundsCalculator calculator;

  public MouseWheelMagnifyListener(BoundsCalculator calculator) {
    this.calculator = calculator;
  }

  public void mouseWheelMoved(MouseWheelEvent event) {
    JGVTComponent component = (JGVTComponent) event.getSource();
    double scale = 1 + 0.1 * event.getWheelRotation();
    int xCenter = component.getWidth() / 2;
    int yCenter = component.getHeight() / 2;
    AffineTransform at = AffineTransform.getTranslateInstance(xCenter, yCenter);
    at.scale(scale, scale);
    at.translate(-xCenter, -yCenter);
    AffineTransform rt = (AffineTransform) component.getRenderingTransform().clone();
    rt.preConcatenate(at);
    component.setRenderingTransform(rt);
    calculator.reset();
  }
}