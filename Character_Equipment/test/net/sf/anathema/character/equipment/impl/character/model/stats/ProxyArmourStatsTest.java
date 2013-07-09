package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.InertBaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.ReactiveBaseMaterial;
import org.junit.Test;

import static net.sf.anathema.equipment.core.MagicalMaterial.Adamant;
import static net.sf.anathema.equipment.core.MagicalMaterial.Moonsilver;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProxyArmourStatsTest {

  @Test
  public void hasOriginalMobilityPenaltyWithoutMagicalMaterial() throws Exception {
    ArmourStats original = createArmorWithMobilityPenalty();
    ProxyArmourStats stats = new ProxyArmourStats(original, new InertBaseMaterial());
    assertThat(stats.getMobilityPenalty(), is(-2));
  }

  @Test
  public void hasNoMobilityPenaltyWithMoonsilver() throws Exception {
    ArmourStats original = createArmorWithMobilityPenalty();
    ProxyArmourStats stats = new ProxyArmourStats(original, new ReactiveBaseMaterial(Moonsilver));
    assertThat(stats.getMobilityPenalty(), is(0));
  }

  @Test
  public void hasImprovedMobilityPenaltyWithAdamant() throws Exception {
    ArmourStats original = createArmorWithMobilityPenalty();
    ProxyArmourStats stats = new ProxyArmourStats(original, new ReactiveBaseMaterial(Adamant));
    assertThat(stats.getMobilityPenalty(), is(-1));
  }

  private ArmourStats createArmorWithMobilityPenalty() {
    ArmourStats original = new ArmourStats();
    original.setMobilityPenalty(-2);
    return original;
  }
}