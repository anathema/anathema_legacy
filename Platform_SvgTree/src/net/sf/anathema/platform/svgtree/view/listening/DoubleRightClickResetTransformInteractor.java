package net.sf.anathema.platform.svgtree.view.listening;

import org.apache.batik.swing.gvt.AbstractResetTransformInteractor;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class DoubleRightClickResetTransformInteractor extends AbstractResetTransformInteractor {

  @Override
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
}