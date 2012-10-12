package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.ListSelectionModel;

public class SingleSelectionActionAddableListView<T> extends ActionAddableListView<T> {

  public SingleSelectionActionAddableListView(Class<T> contentClass) {
    super(null, contentClass);
    getList().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }
}
