package net.sf.anathema.character.impl.module;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.toolbar.ToolBarUtilities;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ToggleButtonPanel implements IToggleButtonPanel, IView {
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final JPanel panel = new JPanel(new GridDialogLayout(2, false));

  @Override
  public JComponent getComponent() {
    return panel;
  }

  @Override
  public JToggleButton addButton(Action action, String text) {
    JToggleButton toggleButton = new JToggleButton(action);
    ToolBarUtilities.configureToolBarButton(toggleButton);
    buttonGroup.add(toggleButton);
    panel.add(toggleButton);
    panel.add(new JLabel(text));
    return toggleButton;
  }
}