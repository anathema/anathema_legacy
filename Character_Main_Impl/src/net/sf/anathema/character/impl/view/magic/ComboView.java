package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.view.magic.IComboView;
import net.sf.anathema.lib.gui.GuiUtilities;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.Action;
import javax.swing.JLabel;
import java.awt.Component;

public class ComboView implements IComboView {

  private final JXTaskPane taskPaneGroup = new JXTaskPane();
  private JLabel label;
  private final Action deleteAction;
  private final Action editAction;
  private Component deleteComponent;

  public ComboView(Action deleteAction, Action editAction) {
    this.deleteAction = deleteAction;
    this.editAction = editAction;
  }

  public JXTaskPane getTaskGroup() {
    return taskPaneGroup;
  }

  public void initGui(String name, String description) {
    label = new JLabel(description);
    taskPaneGroup.add(label);
    taskPaneGroup.add(editAction);
    deleteComponent = taskPaneGroup.add(deleteAction);
    taskPaneGroup.setTitle(name);
  }

  public void updateCombo(String name, String description) {
    taskPaneGroup.setTitle(name);
    label.setText(description);
    GuiUtilities.revalidate(taskPaneGroup);
  }

  public void setEditText(String text) {
    editAction.putValue(Action.NAME, text);
  }

  public void setEditButtonsVisible(boolean enabled) {
    if (!enabled) {
      taskPaneGroup.remove(deleteComponent);
      GuiUtilities.revalidateTree(taskPaneGroup);
    } else {
      // todo vom (04.04.2005) (sieroux): Komponenten einfuegen falls noch nicht enthalten
    }
  }
}
