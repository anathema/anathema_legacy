package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.InertBaseMaterial;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MaterialSpeedModifierTest {

  @Test
  public void reducesBy1IfJade() throws Exception {
    BaseMaterial baseMaterial = mock(BaseMaterial.class);
    when(baseMaterial.isJadeBased()).thenReturn(true);
    int calculate = new MaterialSpeedModifier(baseMaterial).calculate();
    assertThat(calculate, is(1));
  }

  @Test
  public void returnsUnmodifiedOtherwise() throws Exception {
    int calculate = new MaterialSpeedModifier(new InertBaseMaterial()).calculate();
    assertThat(calculate, is(0));
  }
}