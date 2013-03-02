package net.sf.anathema.character.platform.module;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.toolbar.ToolBarUtilities;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.constraintsForImageButton;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class ToggleButtonPanel implements IToggleButtonPanel, IView {
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final JPanel panel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2)));

  @Override
  public JComponent getComponent() {
    return panel;
  }

  @Override
  public JToggleButton addButton(Action action, String text) {
    JToggleButton toggleButton = new JToggleButton(action);
    ToolBarUtilities.configureToolBarButton(toggleButton);
    buttonGroup.add(toggleButton);
    panel.add(toggleButton, constraintsForImageButton(toggleButton));
    panel.add(new JLabel(text));
    return toggleButton;
  }
}