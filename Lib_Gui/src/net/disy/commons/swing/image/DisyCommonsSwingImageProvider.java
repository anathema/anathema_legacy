/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.image;

public class DisyCommonsSwingImageProvider extends ImageProvider {

  private final static DisyCommonsSwingImageProvider instance = new DisyCommonsSwingImageProvider();

  public static DisyCommonsSwingImageProvider getInstance() {
    return instance;
  }

  private DisyCommonsSwingImageProvider() {
    super("net/disy/commons/swing/icons"); //$NON-NLS-1$
  }

}