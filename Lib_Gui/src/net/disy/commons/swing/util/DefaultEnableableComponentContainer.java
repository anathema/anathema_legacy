/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.util;

import javax.swing.JComponent;

public class DefaultEnableableComponentContainer implements IEnableableComponentContainer {

  private final JComponent panel;

  public DefaultEnableableComponentContainer(final JComponent panel) {
    this.panel = panel;
    GuiUtilities.setContainerEnabled(panel, isEnabled());
  }

  public boolean isEnabled() {
    return panel.isEnabled();
  }

  @Override
  public JComponent[] getComponents() {
    return new JComponent[]{ panel };
  }

  @Override
  public void setEnabled(final boolean enabled) {
    GuiUtilities.setContainerEnabled(panel, enabled);
  }
}