package net.sf.anathema.character.impl.model.traits;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class EssenceLimitationListenerTest {

  @Test
  public void doesNotTriggerResetUntilCharacterIsFullyLoaded() throws Exception {
    ICharacterModelContext context = mock(ICharacterModelContext.class);
    TraitProvider traits = mock(TraitProvider.class);
    ITrait trait = mock(IDefaultTrait.class);
    when(traits.iterator()).thenReturn(Lists.newArrayList(trait).iterator());
    new EssenceLimitationListener(traits, context).valueChanged(7);
    verifyZeroInteractions(trait);
  }
}
