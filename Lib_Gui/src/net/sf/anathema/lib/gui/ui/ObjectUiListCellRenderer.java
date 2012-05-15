/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.ui;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.gui.icon.DisabledIconDecorator;
import net.sf.anathema.lib.lang.StringUtilities;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import java.awt.Component;

public class ObjectUiListCellRenderer extends DefaultListCellRenderer {

  private final IObjectUi ui;

  public ObjectUiListCellRenderer(final IObjectUi ui) {
    Ensure.ensureArgumentNotNull(ui);
    this.ui = ui;
  }

  @Override
  public Component getListCellRendererComponent(
      final JList list,
      final Object value,
      final int index,
      final boolean isSelected,
      final boolean cellHasFocus) {
    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    Icon icon = ui.getIcon(value);
    if (icon != null && !list.isEnabled()) {
      icon = new DisabledIconDecorator(icon);
    }
    setIcon(icon);
    setDisabledIcon(icon == null ? null : new DisabledIconDecorator(icon));
    final String label = ui.getLabel(value);
    setText(label == null || label.equals("") ? " " : label); //$NON-NLS-1$ //$NON-NLS-2$
    final String toolTipText = ui.getToolTipText(value);
    setToolTipText(StringUtilities.isNullOrEmpty(toolTipText) ? null : toolTipText);
    return this;
  }
}