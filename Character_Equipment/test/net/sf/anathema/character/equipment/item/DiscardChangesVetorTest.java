package net.sf.anathema.character.equipment.item;

import net.sf.anathema.hero.dummy.DummyCondition;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.dummy.DummyResources;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DiscardChangesVetorTest {

  @Test
  public void executesCommandWhenPreconditionForInteractionIsNotMet() throws Exception {
    DummyCondition condition = new DummyCondition();
    condition.setValue(false);
    Command command = mock(Command.class);
    DiscardChangesVetor vetor = new DiscardChangesVetor(new DummyResources(), condition);
    vetor.requestPermissionFor(command);
    verify(command).execute();
  }
}