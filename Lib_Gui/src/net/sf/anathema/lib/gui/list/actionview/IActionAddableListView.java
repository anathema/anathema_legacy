package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.Action;

public interface IActionAddableListView<T> {
  void setObjects(T[] items);

  void addListSelectionListener(Runnable listener);

  T[] getSelectedItems();

  void addAction(Action action);
}