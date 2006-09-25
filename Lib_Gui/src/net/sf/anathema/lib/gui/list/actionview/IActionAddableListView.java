package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.Action;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.lib.gui.IView;

public interface IActionAddableListView<T> extends IView {

  public void setObjects(T[] items);

  public void setListTitle(String title);

  public void addListSelectionListener(ListSelectionListener listener);

  public T[] getSelectedItems();

  public void addAction(Action action);

  public void refreshView();
}