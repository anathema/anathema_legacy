package net.sf.anathema.lib.gui.list;

import javax.swing.ListSelectionModel;

public enum ListSelectionMode {
  SingleSelection {
    @Override
    public int getMode() {
      return ListSelectionModel.SINGLE_SELECTION;
    }
  },
  SingleIntervalSelection {
    @Override
    public int getMode() {
      return ListSelectionModel.SINGLE_INTERVAL_SELECTION;
    }
  },
  MultipleIntervalSelection {
    @Override
    public int getMode() {
      return ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
    }
  };

  public abstract int getMode();
}