/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.widgets;

import net.sf.anathema.lib.gui.swing.LookAndFeelUtilities;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;

/**
 * Component similar to JLabel, but automatically wrapping text.
 * @author gebhard
 */
public class AutoWrappingLabel {

  private final static int INITIAL_WIDTH = 330;

  //10.11.2007 (Markus Gebhard): I have tried to implement this using a JTextArea, but this failed.
  // Problem is the size: there is no good way to find out the required height in order not to crop
  // the text. So after trying really hard, I decided to write my own text swing
  private final AutoWrappingTextComponent component;

  public AutoWrappingLabel() {
    this(""); //$NON-NLS-1$
  }

  public AutoWrappingLabel(final String text) {
    this(text, INITIAL_WIDTH);
  }

  public AutoWrappingLabel(final String text, final int width) {
    component = new AutoWrappingTextComponent(text, width);
    LookAndFeelUtilities.installColorsAndFont(component, LookAndFeelUtilities.COMPONENT_TYPE_LABEL);
  }

  public void setFont(final Font font) {
    component.setFont(font);
  }

  public JComponent getContent() {
    return component;
  }

  public void setForeground(final Color color) {
    component.setForeground(color);
  }

  public void setBackground(final Color color) {
    component.setBackground(color);
  }

  public void setText(final String text) {
    component.setText(text);
  }

  public void setOpaque(final boolean opaque) {
    component.setOpaque(opaque);
  }
}