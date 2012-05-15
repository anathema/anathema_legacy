/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
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

  private RelativePosition() {
    //nothing to do
  }

  public void place(final Window window) {
    final Window owner = window.getOwner();
    if (owner != null && owner.isVisible()) {
      window.setLocation(createLocation(window.getBounds(), window.getOwner().getBounds()));
    }
    else {
      GuiUtilities.centerOnScreen(window);
    }
  }

  protected abstract Point createLocation(Rectangle windowBounds, Rectangle ownerBounds);

}