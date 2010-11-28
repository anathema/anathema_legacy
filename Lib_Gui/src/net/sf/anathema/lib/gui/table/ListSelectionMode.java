//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table;

import javax.swing.ListSelectionModel;

// NOT_PUBLISHED
public class ListSelectionMode {

  private final int listSelectionMode;

  private ListSelectionMode(int listSelectionMode) {
    this.listSelectionMode = listSelectionMode;
  }

  public int getListSelectionMode() {
    return listSelectionMode;
  }

  public final static ListSelectionMode SINGLE_SELECTION = new ListSelectionMode(ListSelectionModel.SINGLE_SELECTION);

  public final static ListSelectionMode MULTIPLE_INTERVAL_SELECTION = new ListSelectionMode(
      ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

  public final static ListSelectionMode SINGLE_INTERVAL_SELECTION = new ListSelectionMode(
      ListSelectionModel.SINGLE_INTERVAL_SELECTION);

}