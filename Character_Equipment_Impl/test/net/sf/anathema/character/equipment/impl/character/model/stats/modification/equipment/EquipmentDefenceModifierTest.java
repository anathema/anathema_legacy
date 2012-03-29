package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentDefenceModifierTest {
  IEquipmentModifiers modifiers = mock(IEquipmentModifiers.class);

  @Test
  public void returnsParryDefenceModifier() throws Exception {
    when(modifiers.getPDVMod()).thenReturn(5);
    EquipmentDefenceModifier modifier = new EquipmentDefenceModifier(modifiers);
    assertThat(modifier.calculate(), is(5));
  }
}
