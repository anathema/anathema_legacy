package net.sf.anathema.hero.spiritual.model;

import com.google.common.collect.Lists;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitIterable;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spiritual.model.traits.EssenceLimitationListener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class EssenceLimitationListenerTest {

  @Test
  public void doesNotTriggerResetUntilCharacterIsFullyLoaded() throws Exception {
    Hero hero = mock(Hero.class);
    TraitIterable traits = mock(TraitIterable.class);
    Trait trait = mock(Trait.class);
    when(traits.iterator()).thenReturn(Lists.newArrayList(trait).iterator());
    new EssenceLimitationListener(traits, hero).valueChanged(7);
    verifyZeroInteractions(trait);
  }
}
