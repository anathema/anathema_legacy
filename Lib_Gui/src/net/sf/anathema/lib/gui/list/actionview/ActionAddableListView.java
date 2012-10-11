package net.sf.anathema.lib.gui.list.actionview;

import net.sf.anathema.lib.gui.list.SmartJList;

import javax.swing.JComponent;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ActionAddableListView<T> extends AbstractActionAddableListView<T> implements
    IMultiSelectionActionAddableListView<T> {

  private final SmartJList<T> list;

  public ActionAddableListView(String title, Class<T> contentClass) {
    super(title);
    list = new SmartJList<T>(contentClass);
  }

  @Override
  public void setObjects(T[] items) {
    list.setObjects(items);
  }

  @Override
  public void addListSelectionListener(final Runnable listener) {
    list.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        listener.run();
      }
    });
  }

  @Override
  public T[] getSelectedItems() {
    return list.getSelectedValues();
  }

  @Override
  public int[] getSelectedIndices() {
    return list.getSelectedIndices();
  }

  @Override
  protected JComponent getDisplayComponent() {
    return list;
  }

  @Override
  protected boolean isScrollable() {
    return true;
  }

  protected SmartJList<T> getList() {
    return list;
  }

  public void setListCellRenderer(ListCellRenderer renderer) {
    list.setCellRenderer(renderer);
  }
}