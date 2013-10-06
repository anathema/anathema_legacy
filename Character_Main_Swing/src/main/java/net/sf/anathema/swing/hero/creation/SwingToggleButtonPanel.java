package net.sf.anathema.swing.hero.creation;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.view.toolbar.ToolbarUtilities;
import net.sf.anathema.hero.creation.ToggleButtonPanel;
import net.sf.anathema.interaction.ToggleTool;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;
import static net.sf.anathema.lib.gui.layout.SwingLayoutUtils.constraintsForImageButton;

public class SwingToggleButtonPanel implements ToggleButtonPanel, IView {
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final JPanel panel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2)));

  @Override
  public JComponent getComponent() {
    return panel;
  }

  @Override
  public ToggleTool addButton(String label) {
    SwingToggleTool tool = new SwingToggleTool();
    JToggleButton button = tool.getButton();
    ToolbarUtilities.configureToolBarButton(button);
    buttonGroup.add(button);
    panel.add(button, constraintsForImageButton(button));
    panel.add(new JLabel(label));
    return tool;
  }
}