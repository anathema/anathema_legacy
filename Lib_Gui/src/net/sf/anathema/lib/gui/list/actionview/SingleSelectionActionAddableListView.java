package net.sf.anathema.lib.gui.list.actionview;

import javax.swing.ListSelectionModel;

public class SingleSelectionActionAddableListView extends ActionAddableListView {

  public SingleSelectionActionAddableListView(String title) {
    super(title);
    getList().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }
}
