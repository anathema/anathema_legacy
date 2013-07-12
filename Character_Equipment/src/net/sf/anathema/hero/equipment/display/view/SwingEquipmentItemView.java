package net.sf.anathema.hero.equipment.display.view;

import net.sf.anathema.character.main.library.taskpane.ITaskPaneGroupView;
import net.sf.anathema.character.main.view.AddToTaskPane;
import net.sf.anathema.hero.equipment.display.presenter.StatsView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.swing.interaction.ActionInteraction;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.JLabel;

public class SwingEquipmentItemView implements net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView, ITaskPaneGroupView {
  private final JXTaskPane taskGroup = new JXTaskPane();
  private final JLabel descriptionLabel = new JLabel();

  public SwingEquipmentItemView() {
    taskGroup.add(descriptionLabel);
  }

  @Override
  public void setItemTitle(String title) {
    taskGroup.setTitle(title);
  }

  @Override
  public void setItemDescription(String text) {
    descriptionLabel.setText(text);
    net.sf.anathema.lib.gui.swing.GuiUtilities.revalidate(taskGroup);
  }

  @Override
  public void clear() {
    taskGroup.removeAll();
    taskGroup.add(descriptionLabel);
  }

  @Override
  public StatsView addStats(String description) {
    CheckBoxStatsView checkBoxView = new CheckBoxStatsView(description);
    taskGroup.add(checkBoxView.getComponent());
    return checkBoxView;
  }

  @Override
  public JXTaskPane getTaskGroup() {
    return taskGroup;
  }

  @Override
  public Tool addAction() {
    ActionInteraction tool = new ActionInteraction();
    tool.addTo(new AddToTaskPane(taskGroup));
    return tool;
  }
}