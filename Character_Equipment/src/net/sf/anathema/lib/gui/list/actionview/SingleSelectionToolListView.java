package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.ListSelectionModel;

public class SingleSelectionToolListView<T> extends SwingToolListView<T> {

  public SingleSelectionToolListView(Class<T> contentClass) {
    super(contentClass);
    getList().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }
}
