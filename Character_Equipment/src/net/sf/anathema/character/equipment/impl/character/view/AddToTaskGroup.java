package net.sf.anathema.character.equipment.impl.character.view;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.Action;

public class AddToTaskGroup implements AddToSwingComponent {
  private JXTaskPane taskGroup;

  public AddToTaskGroup(JXTaskPane taskGroup) {
    this.taskGroup = taskGroup;
  }

  @Override
  public void add(Action action) {
    taskGroup.add(action);
  }
}
