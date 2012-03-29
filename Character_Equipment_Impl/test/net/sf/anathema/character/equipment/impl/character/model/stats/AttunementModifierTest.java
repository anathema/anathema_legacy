package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttunementModifierTest {
  StatModifier delegate = mock(StatModifier.class);

  @Test
  public void doesNotModifyWhenUnattuned() throws Exception {
    AttunementModifier modifier = new AttunementModifier(delegate, false);
    assertThat(modifier.calculate(), is(0));
  }

  @Test
  public void returnsOriginalValueOtherwise() throws Exception {
    when(delegate.calculate()).thenReturn(5);
    AttunementModifier modifier = new AttunementModifier(delegate, true);
    assertThat(modifier.calculate(), is(5));
  }
}
