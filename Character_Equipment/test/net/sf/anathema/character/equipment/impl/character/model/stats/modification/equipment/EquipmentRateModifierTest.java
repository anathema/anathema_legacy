package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentRateModifierTest {
  IWeaponModifiers modifiers = mock(IWeaponModifiers.class);

  @Test
  public void returnsRateModifierForMelee() throws Exception {
    when(modifiers.getMeleeRateMod()).thenReturn(5);
    EquipmentRateModifier modifier = new EquipmentRateModifier(modifiers, WeaponStatsType.Melee);
    assertThat(modifier.calculate(), is(5));
  }

  @Test
  public void returnsRateModifierForRanged() throws Exception {
    when(modifiers.getRangedRateMod()).thenReturn(4);
    EquipmentRateModifier modifier = new EquipmentRateModifier(modifiers, WeaponStatsType.Bow);
    assertThat(modifier.calculate(), is(4));
  }
}
