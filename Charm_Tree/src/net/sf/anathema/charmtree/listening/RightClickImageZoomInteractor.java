package net.sf.anathema.charmtree.listening;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import net.sf.anathema.charmtree.batik.BoundsCalculator;

import org.apache.batik.swing.gvt.AbstractImageZoomInteractor;

public class RightClickImageZoomInteractor extends AbstractImageZoomInteractor {

  private final BoundsCalculator calculator;

  public RightClickImageZoomInteractor(BoundsCalculator calculator) {
    this.calculator = calculator;
  }

  @Override
  public boolean startInteraction(InputEvent ie) {
    int mods = ie.getModifiers();
    return ie.getID() == MouseEvent.MOUSE_PRESSED && (mods & InputEvent.BUTTON3_MASK) != 0;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    super.mouseReleased(e);
    calculator.reset();
  }
}