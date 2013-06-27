package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.dummy.DummyBasicCharacterData;
import net.sf.anathema.character.generic.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmHasSameTypeAsCharacterTest {

  @Test
  public void identifiesMatchingCharacterTypesIfTheyAreNotIdentical() throws Exception {
    ICharacterModelContext context = createCharacterWithType(new DummyExaltCharacterType());
    DummyCharm charm = createCharmForCharacterType(new DummyExaltCharacterType());
    CharmHasSameTypeAsCharacter predicate = new CharmHasSameTypeAsCharacter(context);
    assertThat(predicate.apply(charm), is(true));
  }

  private DummyCharm createCharmForCharacterType(DummyExaltCharacterType characterTypeForCharm) {
    DummyCharm charm = new DummyCharm();
    charm.setCharacterType(characterTypeForCharm);
    return charm;
  }

  private ICharacterModelContext createCharacterWithType(DummyExaltCharacterType characterTypeForCharacter) {
    ICharacterModelContext context = mock(ICharacterModelContext.class);
    DummyBasicCharacterData basicContext = new DummyBasicCharacterData();
    basicContext.setCharacterType(characterTypeForCharacter);
    when(context.getBasicCharacterContext()).thenReturn(basicContext);
    return context;
  }
}