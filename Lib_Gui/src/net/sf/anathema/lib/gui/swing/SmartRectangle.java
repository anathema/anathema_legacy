/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.swing;

import net.disy.commons.core.util.Ensure;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class SmartRectangle extends Rectangle {

  public SmartRectangle(final Rectangle rect) {
    this(rect.x, rect.y, rect.width, rect.height);
  }

  public SmartRectangle(final Point position, final Dimension size) {
    this(position.x, position.y, size.width, size.height);
  }

  public SmartRectangle(final int ulx, final int uly, final int width, final int height) {
    super(ulx, uly, width, height);
    Ensure.ensureArgumentTrue("Width <0 : '" + width + "'", width >= 0); //$NON-NLS-1$ //$NON-NLS-2$
    Ensure.ensureArgumentTrue("Height <0 : '" + height + "'", height >= 0); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public int getUlx() {
    return x;
  }

  public int getUly() {
    return y;
  }

  public int getLrx() {
    return x + width;
  }

  public int getLry() {
    return y + height;
  }

  /**
   * Move the box
   *
   * @param dx x coordinate of the moving vector
   * @param dy y coordinate of the moving vectot
   */
  @Override
  public void setLocation(final int dx, final int dy) {
    x += dx;
    y += dy;
  }

  public Point getCenter() {
    return new Point((getLrx() + getUlx()) / 2, (getLry() + getUly()) / 2);
  }
}