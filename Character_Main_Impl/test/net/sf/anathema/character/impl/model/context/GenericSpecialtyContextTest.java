package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.control.change.IChangeListener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GenericSpecialtyContextTest {
  IGenericCharacter character = mock(IGenericCharacter.class);
  GenericSpecialtyContext context = new GenericSpecialtyContext(character);

  @Test
  public void addsSpecialtyListenerToCharacter() throws Exception {
    IChangeListener listener = mock(IChangeListener.class);
    context.addSpecialtyListChangeListener(listener);
    verify(character).addSpecialtyListChangeListener(listener);
  }

  @Test
  public void retrievesSpecialtiesFromCharacter() throws Exception {
    ITraitType trait = AbilityType.Archery;
    context.getSpecialties(trait);
    verify(character).getSpecialties(trait);
  }
}
