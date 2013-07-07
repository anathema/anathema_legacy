package net.sf.anathema.character.model.view.magic;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.swing.interaction.ActionInteraction;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.JLabel;

public class ComboView implements IComboView {

  private final JXTaskPane taskPaneGroup = new JXTaskPane();
  private JLabel label;

  public JXTaskPane getTaskGroup() {
    return taskPaneGroup;
  }

  @Override
  public void initGui(String name, String description) {
    label = new JLabel(description);
    taskPaneGroup.add(label);
    taskPaneGroup.setTitle(name);
  }

  @Override
  public void updateCombo(String name, String description) {
    taskPaneGroup.setTitle(name);
    label.setText(description);
    GuiUtilities.revalidate(taskPaneGroup);
  }

  @Override
  public Tool addTool() {
    ActionInteraction interaction = new ActionInteraction();
    interaction.addTo(new AddToTaskPane(taskPaneGroup));
    return interaction;
  }
}