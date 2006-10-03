package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

import org.junit.Test;

public abstract class AbstractFirstEditionSoulsteelModificationTest extends AbstractSoulsteelModificationTest {

  @Test
  public final void accuracyIncreasedForMeleeBy1() throws Exception {
    assertAccuracyModification(2, 1, WeaponStatsType.Melee);
    assertAccuracyModification(1, 1, WeaponStatsType.Bow);
    assertAccuracyModification(1, 1, WeaponStatsType.Thrown);
    assertAccuracyModification(1, 1, WeaponStatsType.Flame);
  }
}