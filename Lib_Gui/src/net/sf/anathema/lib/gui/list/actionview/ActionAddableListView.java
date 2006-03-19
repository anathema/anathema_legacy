package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.JComponent;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.lib.gui.list.SmartJList;

public class ActionAddableListView extends AbstractActionAddableListView implements
    IMultiSelectionActionAddableListView {

  private final SmartJList list = new SmartJList();

  public ActionAddableListView(String title) {
    super(title);
  }

  public void setListItems(Object[] items) {
    list.setObjects(items);
  }

  public void addListSelectionListener(ListSelectionListener listener) {
    list.addListSelectionListener(listener);
  }

  public Object[] getSelectedItems() {
    return list.getSelectedValues();
  }

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

  protected SmartJList getList() {
    return list;
  }

  public void setListCellRenderer(ListCellRenderer renderer) {
    list.setCellRenderer(renderer);
  }
}