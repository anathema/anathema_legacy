package net.sf.anathema.character.equipment.impl.character.model.stats.modification.modifier;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BestModifierTest {

  @Test
  public void hasBaseValueOfZero() throws Exception {
    int calculate = new BestModifier().calculate();
    assertThat(calculate, is(0));
  }

  @Test
  public void returnsResultFromSingleDelegate() throws Exception {
    StatModifier modifier = mock(StatModifier.class);
    when(modifier.calculate()).thenReturn(2);
    int calculate = new BestModifier(modifier).calculate();
    assertThat(calculate, is(2));
  }

  @Test
  public void returnsBestFromTwoDelegate() throws Exception {
    StatModifier modifier1 = mock(StatModifier.class);
    when(modifier1.calculate()).thenReturn(5);
    StatModifier modifier2 = mock(StatModifier.class);
    when(modifier2.calculate()).thenReturn(2);
    int calculate = new BestModifier(modifier1, modifier2).calculate();
    assertThat(calculate, is(5));
  }
}