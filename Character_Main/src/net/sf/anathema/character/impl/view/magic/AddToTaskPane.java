package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.Action;

public class AddToTaskPane implements AddToSwingComponent {
  private JXTaskPane taskPaneGroup;

  public AddToTaskPane(JXTaskPane taskPaneGroup) {
    this.taskPaneGroup = taskPaneGroup;
  }

  @Override
  public void add(Action action) {
    taskPaneGroup.add(action);
  }
}
