package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.ListSelectionModel;

public class SingleSelectionActionAddableListView<T> extends ActionAddableListView<T> {

  public SingleSelectionActionAddableListView(String title, Class<T> contentClass) {
    super(title, contentClass);
    getList().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }
}
