package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.Action;
import javax.swing.event.ListSelectionListener;

public interface IActionAddableListView<T> {

  void setObjects(T[] items);

  void setListTitle(String title);

  void addListSelectionListener(ListSelectionListener listener);

  T[] getSelectedItems();

  void addAction(Action action);

  void refreshView();
}