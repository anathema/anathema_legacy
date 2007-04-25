package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.swing.gvt.JGVTComponent;

public class RightClickPanAdapter extends MouseAdapter {

  private final IBoundsCalculator calculator;

  public RightClickPanAdapter(IBoundsCalculator boundsCalculator) {
    this.calculator = boundsCalculator;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == 0 || e.getClickCount() != 1) {
      return;
    }
    JGVTComponent component = (JGVTComponent) e.getSource();
    int xCenter = component.getWidth() / 2;
    int yCenter = component.getHeight() / 2;
    AffineTransform at = AffineTransform.getTranslateInstance(xCenter - e.getX(), yCenter - e.getY());
    AffineTransform rt = (AffineTransform) component.getRenderingTransform().clone();
    rt.preConcatenate(at);
    component.setRenderingTransform(rt);
    calculator.reset();
  }
}