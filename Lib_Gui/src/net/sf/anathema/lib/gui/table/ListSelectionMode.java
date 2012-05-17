package net.sf.anathema.lib.gui.table;

import javax.swing.ListSelectionModel;

public class ListSelectionMode {

  private final int listSelectionMode;

  private ListSelectionMode(int listSelectionMode) {
    this.listSelectionMode = listSelectionMode;
  }

  public int getListSelectionMode() {
    return listSelectionMode;
  }

  public static final ListSelectionMode SINGLE_SELECTION = new ListSelectionMode(ListSelectionModel.SINGLE_SELECTION);
}