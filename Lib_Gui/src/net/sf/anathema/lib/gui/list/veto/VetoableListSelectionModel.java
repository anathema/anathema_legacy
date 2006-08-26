// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.list.veto;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ISimpleBlock;

// NOT_PUBLISHED
public class VetoableListSelectionModel extends DefaultListSelectionModel {

  private List<IVetor> vetors = new ArrayList<IVetor>();
  private boolean alreadyAsked;

  public VetoableListSelectionModel() {
    setSelectionMode(SINGLE_SELECTION);
  }

  @Override
  public void addSelectionInterval(final int index0, final int index1) {
    executeVetoable(new ISimpleBlock() {
      public void execute() {
        VetoableListSelectionModel.super.addSelectionInterval(index0, index1);
      }
    });
  }

  private synchronized void executeVetoable(ISimpleBlock block) {
    if (alreadyAsked) {
      block.execute();
      return;
    }
    if (vetos()) {
      return;
    }
    alreadyAsked = true;
    block.execute();
    alreadyAsked = false;
  }

  public synchronized void addVetor(IVetor vetor) {
    vetors.add(vetor);
  }

  @Override
  public void removeSelectionInterval(final int index0, final int index1) {
    if (getMaxSelectionIndex() == -1) {
      return;
    }
    executeVetoable(new ISimpleBlock() {
      public void execute() {
        VetoableListSelectionModel.super.removeSelectionInterval(index0, index1);
      }
    });
  }

  public synchronized void removeVetor(IVetor vetor) {
    vetors.remove(vetor);
  }

  @Override
  public void setSelectionInterval(final int index0, final int index1) {
    executeVetoable(new ISimpleBlock() {
      public void execute() {
        VetoableListSelectionModel.super.setSelectionInterval(index0, index1);
      }
    });
  }

  @Override
  public void setSelectionMode(int selectionMode) {
    Ensure.ensureArgumentEquals(ListSelectionModel.SINGLE_SELECTION, selectionMode);
    super.setSelectionMode(selectionMode);
  }

  private synchronized boolean vetos() {
    List<IVetor> cloneList = new ArrayList<IVetor>(vetors);
    for (int index = 0; index < cloneList.size(); index++) {
      IVetor vetor = cloneList.get(index);
      if (vetor.vetos()) {
        return true;
      }
    }
    return false;
  }
}