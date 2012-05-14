/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import javax.swing.Icon;
import javax.swing.JToolBar;

public class DialogHeaderPanelConfiguration implements IDialogHeaderPanelConfiguration {

  public static IDialogHeaderPanelConfiguration createVisibleWithoutIcon() {
    return new DialogHeaderPanelConfiguration(null, true);
  }

  public static IDialogHeaderPanelConfiguration createInvisible() {
    return new DialogHeaderPanelConfiguration(null, false);
  }

  private final Icon icon;
  private final boolean visible;
  private final JToolBar toolBar;

  private DialogHeaderPanelConfiguration(final Icon icon, final boolean visible) {
    this(icon, visible, null);
  }

  private DialogHeaderPanelConfiguration(
      final Icon icon,
      final boolean visible,
      final JToolBar toolBar) {
    this.icon = icon;
    this.visible = visible;
    this.toolBar = toolBar;
  }

  @Override
  public Icon getLargeDialogIcon() {
    return icon;
  }

  @Override
  public boolean isHeaderPanelVisible() {
    return visible;
  }

  @Override
  public JToolBar getToolBar() {
    return toolBar;
  }
}