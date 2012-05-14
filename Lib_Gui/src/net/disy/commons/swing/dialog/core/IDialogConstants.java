/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public interface IDialogConstants {
  public static final Dimension MINIMUM_CONTENT_SIZE = new Dimension(310, 50);

  public static final Font MESSAGE_LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 11);
  public static final Font HEADER_TITLE_FONT = new Font(Font.DIALOG, Font.BOLD, 13);

  //Always use black on white color for header panel. There are no L&F colors defined for dialog headers 
  public static final Color HEADER_TEXT_COLOR = Color.BLACK;

  //Always use a bright grey color for header panel background, since there are no L&F colors defined for dialog headers 
  public static final Color HEADER_BACKGROUND_COLOR = Color.WHITE;

  public static final Color HEADER_OVERLAID_BORDER_COLOR = Color.DARK_GRAY;
}