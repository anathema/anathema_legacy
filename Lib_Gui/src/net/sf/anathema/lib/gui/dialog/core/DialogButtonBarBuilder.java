/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.core;

import net.disy.commons.swing.button.RolloverButtonFactory;
import net.disy.commons.swing.component.Gap;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.util.ButtonPanelBuilder;
import net.disy.commons.swing.layout.util.LayoutDirection;
import net.disy.commons.swing.util.IEnableable;
import net.sf.anathema.lib.gui.action.MnemonicLabelParser;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogIconResources;
import net.sf.anathema.lib.gui.dialog.DialogMessages;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogButtonBarBuilder {

  private final List<JComponent> leftSideComponents = new ArrayList<JComponent>();
  private final List<JComponent> buttons = new ArrayList<JComponent>();
  private AbstractButton helpButton;

  public void addLeftSideComponent(final JComponent component) {
    leftSideComponents.add(component);
  }

  public void addButtons(final JComponent... buttonComponents) {
    buttons.addAll(Arrays.asList(buttonComponents));
  }

  public void addButtonsCompacted(final JComponent... buttonComponents) {
    final JPanel compactedButtonPanel = new JPanel(new GridLayout(1, 0, 0, 0));
    for (final JComponent component : buttonComponents) {
      compactedButtonPanel.add(component);
    }
    addButtons(compactedButtonPanel);
  }

  public IEnableable setHelpHandler(final IDialogHelpHandler helpHandler) {
    helpButton = createHelpButton(helpHandler);
    return new IEnableable() {
      @Override
      public void setEnabled(final boolean enabled) {
        helpButton.setEnabled(enabled);
      }
    };
  }

  public JComponent createButtonBar() {
    final ArrayList<JComponent> allLeftComponents = new ArrayList<JComponent>();
    if (helpButton != null) {
      allLeftComponents.add(new Gap(0, 0));//Add a little gap for moving the button to the right a bit 
      allLeftComponents.add(helpButton);
    }
    allLeftComponents.addAll(leftSideComponents);

    final JPanel panel = new JPanel(new GridDialogLayout(allLeftComponents.size() + 1, false));
    for (final JComponent component : allLeftComponents) {
      panel.add(component);
    }
    final ButtonPanelBuilder buttonPanelBuilder = new ButtonPanelBuilder(LayoutDirection.HORIZONTAL);
    for (final JComponent createdButton : buttons) {
      buttonPanelBuilder.add(createdButton);
    }
    final JPanel buttonPanel = buttonPanelBuilder.createPanel();
    panel.add(buttonPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    return panel;
  }

  private static AbstractButton createHelpButton(final IDialogHelpHandler helpHandler) {
    final SmartAction helpAction = new SmartAction(DialogIconResources.DIALOG_HELP) {
      @Override
      protected void execute(final Component parentComponent) {
        helpHandler.execute(parentComponent);
      }
    };
    final String helpText = MnemonicLabelParser
        .parse(DialogMessages.HELP)
        .getPlainText();
    helpAction.setToolTipText(helpText);
    return RolloverButtonFactory.createButton(helpAction);
  }
}