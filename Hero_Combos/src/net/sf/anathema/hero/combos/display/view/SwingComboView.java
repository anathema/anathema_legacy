package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.character.main.view.AddToTaskPane;
import net.sf.anathema.hero.combos.display.presenter.ComboView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.swing.interaction.ActionInteraction;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.JLabel;

public class SwingComboView implements ComboView {

  private final JXTaskPane taskPaneGroup = new JXTaskPane();
  private final JXTaskPaneContainer parent;
  private JLabel label;

  public SwingComboView(JXTaskPaneContainer parent) {
    this.parent = parent;
  }

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

  @Override
  public void remove() {
    parent.remove(taskPaneGroup);
  }
}