/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.icon;

import javax.swing.Icon;
import javax.swing.UIManager;

public class SwingIcons {

  public static Icon getOptionPaneErrorIcon() {
    return UIManager.getIcon("OptionPane.errorIcon"); //$NON-NLS-1$
  }

  public static Icon getOptionPaneWarningIcon() {
    return UIManager.getIcon("OptionPane.warningIcon"); //$NON-NLS-1$
  }

  public static Icon getOptionPaneInformationIcon() {
    return UIManager.getIcon("OptionPane.informationIcon"); //$NON-NLS-1$
  }

  public static Icon getOptionPaneQuestionIcon() {
    return UIManager.getIcon("OptionPane.questionIcon"); //$NON-NLS-1$
  }
}