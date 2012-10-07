package net.sf.anathema.lib.gui.dialog.core;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.lib.gui.layout.ButtonPanelBuilder;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogButtonBarBuilder {
  private final List<JComponent> buttons = new ArrayList<JComponent>();

  public void addButtons(JComponent... buttonComponents) {
    Collections.addAll(buttons, buttonComponents);
  }

  public void addButtonsCompacted(JComponent... buttonComponents) {
    JPanel compactedButtonPanel = new JPanel(new GridLayout(1, 0, 0, 0));
    for (JComponent component : buttonComponents) {
      compactedButtonPanel.add(component);
    }
    addButtons(compactedButtonPanel);
  }

  public JComponent createButtonBar() {
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    ButtonPanelBuilder buttonPanelBuilder = new ButtonPanelBuilder();
    for (JComponent createdButton : buttons) {
      buttonPanelBuilder.add(createdButton);
    }
    JPanel buttonPanel = buttonPanelBuilder.createPanel();
    panel.add(buttonPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    return panel;
  }
}