package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.InertBaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.ReactiveBaseMaterial;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import org.junit.Test;

import static net.sf.anathema.character.equipment.MagicalMaterial.Adamant;
import static net.sf.anathema.character.equipment.MagicalMaterial.Moonsilver;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

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
    ICollectionFactory collectionFactory = mock(ICollectionFactory.class);
    ArmourStats original = new ArmourStats(collectionFactory);
    original.setMobilityPenalty(-2);
    return original;
  }
}