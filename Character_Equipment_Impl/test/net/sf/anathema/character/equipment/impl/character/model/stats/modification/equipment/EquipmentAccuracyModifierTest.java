package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import net.sf.anathema.character.equipment.IEquipmentModifiers;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentAccuracyModifierTest {
  IEquipmentModifiers modifiers = mock(IEquipmentModifiers.class);

  @Test
  public void returnsAccuracyModifierForMelee() throws Exception {
    when(modifiers.getMeleeAccuracyMod()).thenReturn(5);
    EquipmentAccuracyModifier modifier = new EquipmentAccuracyModifier(modifiers, WeaponStatsType.Melee);
    assertThat(modifier.calculate(), is(5));
  }

  @Test
  public void returnsAccuracyModifierForRanged() throws Exception {
    when(modifiers.getRangedAccuracyMod()).thenReturn(4);
    EquipmentAccuracyModifier modifier = new EquipmentAccuracyModifier(modifiers, WeaponStatsType.Bow);
    assertThat(modifier.calculate(), is(4));
  }
}
