package net.sf.anathema.lib.gui.list.veto;

import net.sf.anathema.interaction.Command;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.never;

public class AggregatedVetorTest {

  private final AggregatedVetor vetor = new AggregatedVetor();

  @Test
  public void doesNotVetoWithoutInternalVetors() throws Exception {
    Command command = Mockito.mock(Command.class);
    vetor.requestPermissionFor(command);
    Mockito.verify(command).execute();
  }

  @Test
  public void deniesPermissionIfAnInternalVetorVetoes() throws Exception {
    Command command = Mockito.mock(Command.class);
    Vetor denier = new Denier();
    vetor.addVetor(denier);
    vetor.requestPermissionFor(command);
    Mockito.verify(command, never()).execute();
  }

  @Test
  public void deniesPermissionIfOneVetorOutOfManyDeniesPermission() throws Exception {
    Command command = Mockito.mock(Command.class);
    Vetor allower = new Allower();
    Vetor denier = new Denier();
    vetor.addVetor(allower);
    vetor.addVetor(denier);
    vetor.requestPermissionFor(command);
    Mockito.verify(command, never()).execute();
  }

  @Test
  public void executesIfSingleVetorPermits() throws Exception {
    Command command = Mockito.mock(Command.class);
    Vetor allower = new Allower();
    vetor.addVetor(allower);
    vetor.requestPermissionFor(command);
    Mockito.verify(command).execute();
  }

  @Test
  public void executesIfAllVetorsPermit() throws Exception {
    Command command = Mockito.mock(Command.class);
    Vetor allower = new Allower();
    Vetor anotherAllower = new Allower();
    Vetor yetAnotherAllower = new Allower();
    vetor.addVetor(allower);
    vetor.addVetor(anotherAllower);
    vetor.addVetor(yetAnotherAllower);
    vetor.requestPermissionFor(command);
    Mockito.verify(command).execute();
  }

  private static class Denier implements Vetor {
    @Override
    public boolean vetos() {
      return false;
    }

    @Override
    public void requestPermissionFor(Command command) {
      //nothing to do, permission denied
    }
  }

  private static class Allower implements Vetor {
    @Override
    public boolean vetos() {
      return false;
    }

    @Override
    public void requestPermissionFor(Command command) {
      command.execute();
    }
  }
}
