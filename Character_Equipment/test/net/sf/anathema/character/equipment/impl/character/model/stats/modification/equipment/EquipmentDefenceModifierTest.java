package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentDefenceModifierTest {
  IWeaponModifiers modifiers = mock(IWeaponModifiers.class);

  @Test
  public void addsParryDefenceModifierTwiceBecauseOfLaterDivisionByTwo() throws Exception {
    when(modifiers.getPDVPoolMod()).thenReturn(5);
    EquipmentDefenceModifier modifier = new EquipmentDefenceModifier(modifiers);
    assertThat(modifier.calculate(), is(5));
  }
}
