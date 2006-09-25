package net.sf.anathema.character.library.taskpane;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.gui.GuiUtilities;

import com.l2fprod.common.swing.JTaskPane;

//TODO: Cannot implement IView, because the interface resides in Lib_GUI. Move?
public class TaskPaneView<V extends ITaskPaneGroupView> {

  private final JTaskPane taskPane = new JTaskPane() {
    @Override
    public Dimension getPreferredScrollableViewportSize() {
      return new Dimension(0, 0);
    }
  };
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

  private void revalidateView() {
    GuiUtilities.revalidate(taskPane);
    GuiUtilities.revalidate(taskScrollPane);
  }

  public JComponent getComponent() {
    return taskScrollPane;
  }
}