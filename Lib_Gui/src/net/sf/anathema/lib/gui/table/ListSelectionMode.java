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

  public static final ListSelectionMode MULTIPLE_INTERVAL_SELECTION = new ListSelectionMode(
      ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

  public static final ListSelectionMode SINGLE_INTERVAL_SELECTION = new ListSelectionMode(
      ListSelectionModel.SINGLE_INTERVAL_SELECTION);

}