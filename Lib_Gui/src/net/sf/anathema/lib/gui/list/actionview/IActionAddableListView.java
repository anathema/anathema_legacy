package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.Action;
import javax.swing.event.ListSelectionListener;

public interface IActionAddableListView {

  public void setListItems(Object[] items);

  public void setListTitle(String title);

  public void addListSelectionListener(ListSelectionListener listener);

  public Object[] getSelectedItems();

  public void addAction(Action action);

  public void refreshView();
}