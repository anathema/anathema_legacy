/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.swing;

import javax.swing.JComponent;
import javax.swing.LookAndFeel;

public class LookAndFeelUtilities {

  public static final String COMPONENT_TYPE_LABEL = "Label"; //$NON-NLS-1$

  public static void installColorsAndFont(final JComponent component, final String type) {
    LookAndFeel.installColorsAndFont(component, type + ".background", type + ".foreground", type //$NON-NLS-1$ //$NON-NLS-2$
        + ".font"); //$NON-NLS-1$
  }

}