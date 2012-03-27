package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BaseMaterialTest {
  @Test
  public void equalsOtherBasedOnMaterial() throws Exception {
    assertThat(new BaseMaterial(MagicalMaterial.Jade).equals(new BaseMaterial(MagicalMaterial.Jade)), is(true));
  }

  @Test
  public void doesNotEqualOnSameBasis() throws Exception {
    assertThat(new BaseMaterial(MagicalMaterial.Jade).equals(new BaseMaterial(MagicalMaterial.VitriolJade)), is(false));
  }
}
