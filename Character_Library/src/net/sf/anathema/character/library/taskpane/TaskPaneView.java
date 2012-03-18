package net.sf.anathema.character.library.taskpane;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.gui.GuiUtilities;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;

//TODO: Cannot implement IView, because the interface resides in Lib_GUI. Move?
public class TaskPaneView<V extends ITaskPaneGroupView> {

  private final JXTaskPaneContainer taskPane = new JXTaskPaneContainer();
  private final JScrollPane taskScrollPane = new JScrollPane(taskPane);
  private final List<V> taskViews = new ArrayList<V>();
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
    Ensure.ensureArgumentTrue("Tried to remove unmanaged task view.", taskViews.contains(taskView)); //$NON-NLS-1$
    taskViews.remove(taskView);
    taskPane.remove(taskView.getTaskGroup());
    revalidateView();
  }

  public void revalidateView() {
    GuiUtilities.revalidate(taskPane);
    GuiUtilities.revalidate(taskScrollPane);
  }

  public JComponent getComponent() {
    return taskScrollPane;
  }
}
