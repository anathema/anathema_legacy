package net.sf.anathema.lib.gui.list;

import net.sf.anathema.lib.gui.list.veto.Vetor;

import java.util.ArrayList;
import java.util.List;

public class AggregatedVetor implements Vetor {

  private final List<Vetor> vetors = new ArrayList<>();

  public synchronized void addVetor(Vetor vetor) {
    vetors.add(vetor);
  }

  public synchronized void removeVetor(Vetor vetor) {
    vetors.remove(vetor);
  }

  @Override
  public synchronized boolean vetos() {
    List<Vetor> cloneList = new ArrayList<>(vetors);
    for (Vetor vetor : cloneList) {
      if (vetor.vetos()) {
        return true;
      }
    }
    return false;
  }
}
