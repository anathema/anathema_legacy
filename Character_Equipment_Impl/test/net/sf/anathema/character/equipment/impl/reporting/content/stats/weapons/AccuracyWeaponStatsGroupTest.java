package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccuracyWeaponStatsGroupTest {
  private IGenericTraitCollection collection = createTraitCollection();
  private IResources resource = mock(IResources.class);
  private IEquipmentModifiers modifier = mock(IEquipmentModifiers.class);
  private AccuracyWeaponStatsGroup statsGroup = new AccuracyWeaponStatsGroup(resource, collection, null, null,
          modifier);

  @Test
  public void includesMeleeAccuracy() throws Exception {
    when(modifier.getMeleeAccuracyMod()).thenReturn(5);
    IWeaponStats stats = createMeleeWeaponWithoutBoni();
    assertThat(statsGroup.getFinalValue(stats, 0), is(5));
  }

  @Test
  public void includesRangedAccuracy() throws Exception {
    when(modifier.getRangedAccuracyMod()).thenReturn(5);
    IWeaponStats stats = createRangedWeaponWithoutBoni();
    assertThat(statsGroup.getFinalValue(stats, 0), is(5));
  }

  private IWeaponStats createRangedWeaponWithoutBoni() {
    IWeaponStats stats = mock(IWeaponStats.class);
    when(stats.getTraitType()).thenReturn(AbilityType.Archery);
    when(stats.isRangedCombat()).thenReturn(true);
    return stats;
  }

  private IWeaponStats createMeleeWeaponWithoutBoni() {
    IWeaponStats stats = mock(IWeaponStats.class);
    when(stats.getTraitType()).thenReturn(AbilityType.Melee);
    when(stats.isRangedCombat()).thenReturn(false);
    return stats;
  }

  private IGenericTraitCollection createTraitCollection() {
    IGenericTraitCollection collection = mock(IGenericTraitCollection.class);
    when(collection.getTrait(AttributeType.Dexterity)).thenReturn(new DummyGenericTrait(AttributeType.Dexterity, 0));
    when(collection.getTrait(AbilityType.Melee)).thenReturn(new DummyGenericTrait(AbilityType.Melee, 0));
    when(collection.getTrait(AbilityType.Archery)).thenReturn(new DummyGenericTrait(AbilityType.Archery, 0));
    return collection;
  }
}
