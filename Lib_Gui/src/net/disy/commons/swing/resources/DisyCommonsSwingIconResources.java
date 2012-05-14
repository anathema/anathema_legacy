/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.resources;

import net.disy.commons.swing.image.ImageProvider;

import javax.swing.Icon;

public class DisyCommonsSwingIconResources implements IIconResources {

  public static final Icon COPY = getImageIcon("copy.gif"); //$NON-NLS-1$

  private static Icon getImageIcon(final String relativePath) {
    return new ImageProvider("net/disy/commons/swing/icons").getImageIcon(relativePath); //$NON-NLS-1$
  }
}