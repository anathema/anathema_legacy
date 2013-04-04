package net.sf.anathema.character.library.taskpane;

import com.google.common.base.Preconditions;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;

public class TaskPaneView<V extends ITaskPaneGroupView> {

  private final JXTaskPaneContainer taskPane = new JXTaskPaneContainer();
  private final JScrollPane taskScrollPane = new JScrollPane(taskPane);
  private final List<V> taskViews = new ArrayList<>();
  private final ITaskPaneGroupViewFactory<V> viewFactory;

  public TaskPaneView(ITaskPaneGroupViewFactory<V> viewFactory) {
    this.viewFactory = viewFactory;
  }

  public V addEquipmentObjectView() {
    V taskView = viewFactory.createView();
    taskViews.add(taskView);
    taskPane.add(taskView.getTaskGroup());
    revalidateView();
    return taskView;
  }

  public void removeEquipmentObjectView(V taskView) {
    Preconditions.checkArgument(taskViews.contains(taskView), "Tried to remove unmanaged task view.");
    taskViews.remove(taskView);
    taskPane.remove(taskView.getTaskGroup());
    revalidateView();
  }

  public void revalidateView() {
    net.sf.anathema.lib.gui.swing.GuiUtilities.revalidate(taskPane);
    net.sf.anathema.lib.gui.swing.GuiUtilities.revalidate(taskScrollPane);
  }

  public JComponent getComponent() {
    return taskScrollPane;
  }
}
