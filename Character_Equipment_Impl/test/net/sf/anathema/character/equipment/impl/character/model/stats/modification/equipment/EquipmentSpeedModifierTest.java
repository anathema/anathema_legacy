package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.IEquipmentModifiers;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentSpeedModifierTest {
  IEquipmentModifiers modifiers = mock(IEquipmentModifiers.class);

  @Test
  public void returnsSpeedModifierForMelee() throws Exception {
    when(modifiers.getMeleeSpeedMod()).thenReturn(5);
    EquipmentSpeedModifier modifier = new EquipmentSpeedModifier(modifiers, WeaponStatsType.Melee);
    assertThat(modifier.calculate(), is(5));
  }

  @Test
  public void returnsSpeedModifierForRanged() throws Exception {
    when(modifiers.getRangedSpeedMod()).thenReturn(4);
    EquipmentSpeedModifier modifier = new EquipmentSpeedModifier(modifiers, WeaponStatsType.Bow);
    assertThat(modifier.calculate(), is(4));
  }
}
