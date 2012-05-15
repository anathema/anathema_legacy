/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.icon;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

public class EmptyIcon implements Icon {

  public final static Icon DEFAULT_ICON = new EmptyIcon(new Dimension(16, 16));

  private final Dimension size;

  public EmptyIcon() {
    this(new Dimension(0, 0));
  }

  public EmptyIcon(final Dimension size) {
    this.size = size;
  }

  @Override
  public int getIconHeight() {
    return size.height;
  }

  @Override
  public int getIconWidth() {
    return size.width;
  }

  @Override
  public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
    //nothing to do
  }
}
