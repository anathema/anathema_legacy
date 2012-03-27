package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReactiveBaseMaterialTest {
  @Test
  public void equalsOtherBasedOnMaterial() throws Exception {
    assertThat(new ReactiveBaseMaterial(MagicalMaterial.Jade).equals(new ReactiveBaseMaterial(MagicalMaterial.Jade)), is(true));
  }

  @Test
  public void doesNotEqualOnSameBasis() throws Exception {
    assertThat(new ReactiveBaseMaterial(MagicalMaterial.Jade).equals(new ReactiveBaseMaterial(MagicalMaterial.VitriolJade)), is(false));
  }
}
