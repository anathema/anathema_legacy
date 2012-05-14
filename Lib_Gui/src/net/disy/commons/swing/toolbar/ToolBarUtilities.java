/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.toolbar;

import net.sf.anathema.lib.gui.action.ActionWidgetFactory;
import net.sf.anathema.lib.gui.action.SmartToggleAction;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Insets;

public class ToolBarUtilities {

  private static final Insets TOOLBAR_BUTTON_MARGIN = new Insets(1, 1, 1, 1);

  public static AbstractButton createToolBarButton(final Action action) {
    return createToolBarButton(action, new DefaultToolBarButtonConfiguration());
  }

  public static AbstractButton createToolBarButton(
      final Action action,
      final IToolBarButtonConfiguration configuration) {
    final AbstractButton button;
    if (action instanceof SmartToggleAction) {
      button = ActionWidgetFactory.createToggleButton((SmartToggleAction) action);
    }
    else {
      button = new JButton();
    }
    button.setAction(action);
    configureToolBarButton(button, configuration);
    return button;
  }

  public static void configureToolBarButton(final AbstractButton button) {
    configureToolBarButton(button, new DefaultToolBarButtonConfiguration());
  }

  public static void configureToolBarButton(
      final AbstractButton button,
      final IToolBarButtonConfiguration configuration) {
    button.setFocusPainted(configuration.isFocusPainted());
    button.setMargin(TOOLBAR_BUTTON_MARGIN);

    if (button.getToolTipText() == null) {
      button.setToolTipText(button.getText());
    }
    if (button.getIcon() != null) {
      button.setText(null);
    }
  }
}