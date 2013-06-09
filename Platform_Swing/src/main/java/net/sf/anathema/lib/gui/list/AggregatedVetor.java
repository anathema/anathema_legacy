package net.sf.anathema.lib.gui.list;

import net.sf.anathema.lib.gui.list.veto.IVetor;

import java.util.ArrayList;
import java.util.List;

public class AggregatedVetor implements IVetor {

  private final List<IVetor> vetors = new ArrayList<>();

  public synchronized void addVetor(IVetor vetor) {
    vetors.add(vetor);
  }

  public synchronized void removeVetor(IVetor vetor) {
    vetors.remove(vetor);
  }

  @Override
  public synchronized boolean vetos() {
    List<IVetor> cloneList = new ArrayList<>(vetors);
    for (IVetor vetor : cloneList) {
      if (vetor.vetos()) {
        return true;
      }
    }
    return false;
  }
}
