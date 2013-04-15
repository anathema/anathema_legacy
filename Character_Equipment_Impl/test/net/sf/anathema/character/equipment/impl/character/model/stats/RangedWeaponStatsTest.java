package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RangedWeaponStatsTest {
  private final RangedWeaponStats stats = new RangedWeaponStats();

  @Test
  public void knowsArtilleryAsRangedType() throws Exception {
    stats.addTag(WeaponTag.Artillery);
    assertThat(stats.isRangedCombat(), is(true));
  }

  @Test
  public void knowsGrenadeAsRangedType() throws Exception {
    stats.addTag(WeaponTag.Grenade);
    assertThat(stats.isRangedCombat(), is(true));
  }
}