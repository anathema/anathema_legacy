package net.sf.anathema.character.main.magic.view;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.Action;

public class AddToTaskPane implements AddToSwingComponent {
  private JXTaskPane taskPane;

  public AddToTaskPane(JXTaskPane taskPane) {
    this.taskPane = taskPane;
  }

  @Override
  public void add(Action action) {
    taskPane.add(action);
  }
}