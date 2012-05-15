/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog;

import java.util.Locale;
import java.util.ResourceBundle;

public class DialogMessages {

  private static final String BUNDLE_NAME = "net.sf.anathema.lib.gui.dialog.messages";//$NON-NLS-1$

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(
      BUNDLE_NAME,
      Locale.getDefault());

  public static final String CANCEL = getString("SmartAction.cancel"); //$NON-NLS-1$
  public static final String HELP = getString("SmartAction.help"); //$NON-NLS-1$
  public static final String OK = getString("SmartAction.okay"); //$NON-NLS-1$

  public static final String SELECT_ALL = getString("SmartAction.selectAll"); //$NON-NLS-1$
  public static final String COPY = getString("SmartAction.copy"); //$NON-NLS-1$

  public static final String WIZARD_NEXT = getString("Wizard.SmartNext"); //$NON-NLS-1$
  public static final String WIZARD_BACK = getString("Wizard.SmartBack"); //$NON-NLS-1$
  public static final String WIZARD_FINISH = getString("Wizard.SmartFinish"); //$NON-NLS-1$

  public static String getString(final String key) {
    return RESOURCE_BUNDLE.getString(key);
  }
}