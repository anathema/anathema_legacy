package net.sf.anathema.charmtree.listening;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import net.sf.anathema.charmtree.batik.BoundsCalculator;

import org.apache.batik.swing.gvt.InteractorAdapter;
import org.apache.batik.swing.gvt.JGVTComponent;

public class RightClickPanInteractor extends InteractorAdapter {

  private final BoundsCalculator calculator;

  public RightClickPanInteractor(BoundsCalculator calculator) {
    this.calculator = calculator;
  }

  @Override
  public boolean endInteraction() {
    return true;
  }

  @Override
  public boolean startInteraction(InputEvent ie) {
    if (!(ie instanceof MouseEvent)) {
      return false;
    }
    MouseEvent event = (MouseEvent) ie;
    int mods = ie.getModifiers();
    return ie.getID() == MouseEvent.MOUSE_CLICKED
        && (mods & InputEvent.BUTTON3_MASK) != 0
        && event.getClickCount() == 1;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
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