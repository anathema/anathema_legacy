/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.core;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class DialogDefaults {

  private final static DialogDefaults instance = new DialogDefaults();

  public static DialogDefaults getInstance() {
    return instance;
  }

  private final List<Image> frameIconImages = new ArrayList<Image>();

  private DialogDefaults() {
    //nothing to do 
  }

  public List<Image> getFrameIconImages() {
    return frameIconImages;
  }
}