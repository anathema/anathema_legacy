package net.sf.anathema.lib.gui.swing;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;

public abstract class RelativePosition {

  public static final RelativePosition CENTER = new RelativePosition() {
    @Override
    protected Point createLocation(Rectangle windowBounds, Rectangle ownerBounds) {
      return new Point(
          (int) (ownerBounds.getCenterX() - windowBounds.width / 2),
          (int) (ownerBounds.getCenterY() - windowBounds.height / 2));
    }
  };

  public void place(Window window) {
    Window owner = window.getOwner();
    if (owner != null && owner.isVisible()) {
      window.setLocation(createLocation(window.getBounds(), window.getOwner().getBounds()));
    }
    else {
      GuiUtilities.centerOnScreen(window);
    }
  }

  protected abstract Point createLocation(Rectangle windowBounds, Rectangle ownerBounds);

}