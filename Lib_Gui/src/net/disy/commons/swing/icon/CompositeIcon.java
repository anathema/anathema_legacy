/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.icon;

import net.disy.commons.core.model.AbstractChangeableModel;
import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.Ensure;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Graphics;

public class CompositeIcon extends AbstractChangeableModel implements Icon {

  private final Icon[] icons;
  private final IChangeListener delegatingChangeListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      fireChangeEvent();
    }
  };

  public CompositeIcon(final Icon... icons) {
    Ensure.ensureArgumentNotNull(icons);
    Ensure.ensureArgumentArrayContentsNotNull(icons);
    this.icons = icons;
    for (final Icon icon : icons) {
      if (icon instanceof IChangeableModel) {
        ((IChangeableModel) icon).addChangeListener(delegatingChangeListener);
      }
    }
  }

  @Override
  public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
    for (final Icon icon : icons) {
      icon.paintIcon(c, g, x, y);
    }
  }

  @Override
  public int getIconWidth() {
    if (icons.length == 0) {
      return 0;
    }
    int width = icons[0].getIconWidth();
    for (int i = 1; i < icons.length; ++i) {
      if (icons[i].getIconWidth() > width) {
        width = icons[i].getIconWidth();
      }
    }
    return width;
  }

  @Override
  public int getIconHeight() {
    if (icons.length == 0) {
      return 0;
    }

    int height = icons[0].getIconHeight();
    for (int i = 0; i < icons.length; ++i) {
      if (icons[i].getIconHeight() > height) {
        height = icons[i].getIconHeight();
      }
    }
    return height;
  }
}