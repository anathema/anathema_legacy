package net.sf.anathema.lib.gui.list.veto;

import net.sf.anathema.interaction.Command;

import java.util.ArrayList;
import java.util.Iterator;
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
  public synchronized void requestPermissionFor(Command command) {
    List<Vetor> cloneList = new ArrayList<>(vetors);
    Iterator<Vetor> iterator = cloneList.iterator();
    if (iterator.hasNext()) {
      Vetor currentVetor = iterator.next();
      callInChain(currentVetor, iterator, command);
    } else {
      command.execute();
    }
  }

  private void callInChain(Vetor currentVetor, final Iterator<Vetor> iterator, final Command originalCommand) {
    Command chainCommand = createNextCommand(iterator, originalCommand);
    currentVetor.requestPermissionFor(chainCommand);
  }

  private Command createNextCommand(final Iterator<Vetor> iterator, final Command originalCommand) {
    if (iterator.hasNext()) {
      final Vetor nextVetor = iterator.next();
      return new Command() {
        @Override
        public void execute() {
          callInChain(nextVetor, iterator, originalCommand);
        }
      };
    } else {
      return originalCommand;
    }
  }
}