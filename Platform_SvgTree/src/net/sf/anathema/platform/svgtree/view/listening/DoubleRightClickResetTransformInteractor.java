package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.swing.gvt.AbstractResetTransformInteractor;

public class DoubleRightClickResetTransformInteractor extends AbstractResetTransformInteractor {
  private final IBoundsCalculator calculator;

  public DoubleRightClickResetTransformInteractor(IBoundsCalculator boundsCalculator) {
    this.calculator = boundsCalculator;
  }

  public boolean startInteraction(InputEvent ie) {
    if (!(ie instanceof MouseEvent)) {
      return false;
    }
    MouseEvent event = (MouseEvent) ie;
    int mods = ie.getModifiers();
    return ie.getID() == MouseEvent.MOUSE_CLICKED
        && event.getClickCount() == 2
        && (mods & InputEvent.BUTTON3_MASK) != 0;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);
    calculator.reset();
  }
}