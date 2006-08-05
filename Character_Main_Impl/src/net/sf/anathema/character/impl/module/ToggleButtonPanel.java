package net.sf.anathema.character.impl.module;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.toolbar.ToolBarUtilities;

public class ToggleButtonPanel implements IToggleButtonPanel {
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final JPanel panel = new JPanel(new GridDialogLayout(2, false));

  public Component getContent() {
    return panel;
  }

  public JToggleButton addButton(Action action, String text) {
    JToggleButton toggleButton = new JToggleButton(action);
    ToolBarUtilities.configureToolBarButton(toggleButton);
    buttonGroup.add(toggleButton);
    panel.add(toggleButton);
    panel.add(new JLabel(text));
    return toggleButton;
  }
}