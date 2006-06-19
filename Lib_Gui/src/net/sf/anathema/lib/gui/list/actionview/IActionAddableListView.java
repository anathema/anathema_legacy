package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.Action;
import javax.swing.event.ListSelectionListener;

public interface IActionAddableListView<T> {

  public void setListItems(T[] items);

  public void setListTitle(String title);

  public void addListSelectionListener(ListSelectionListener listener);

  public T[] getSelectedItems();

  public void addAction(Action action);

  public void refreshView();
}