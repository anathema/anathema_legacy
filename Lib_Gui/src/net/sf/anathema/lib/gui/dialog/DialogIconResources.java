/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog;

import net.disy.commons.swing.image.ImageProvider;
import net.disy.commons.swing.resources.IIconResources;

import javax.swing.Icon;

public class DialogIconResources implements IIconResources {

  public static final Icon DIALOG_HEADER_ICON_BACKGROUND = getImageIcon("dialog_header_icon_background.gif"); //$NON-NLS-1$
  public static final Icon DIALOG_HELP = getImageIcon("dialog_help.gif"); //$NON-NLS-1$

  private static Icon getImageIcon(final String relativePath) {
    return new ImageProvider("net/disy/commons/swing/dialog/icons").getImageIcon(relativePath); //$NON-NLS-1$
  }
}