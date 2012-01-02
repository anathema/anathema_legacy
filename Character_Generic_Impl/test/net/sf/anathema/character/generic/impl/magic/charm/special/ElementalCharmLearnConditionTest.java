package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.dummy.DummyBasicCharacterData;
import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.type.CharacterType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.impl.magic.charm.special.Element.Air;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ElementalCharmLearnConditionTest {

  private ICharm charm = mock(ICharm.class);
  private ICharmLearnableArbitrator arbitrator = mock(ICharmLearnableArbitrator.class);
  private Element element = Air;
  private List<ElementalSubeffect> effects = new ArrayList<ElementalSubeffect>();
  private DummyBasicCharacterData character = new DummyBasicCharacterData();
  private ElementalCharmLearnCondition condition = new ElementalCharmLearnCondition(effects, arbitrator, charm, character, element);

  @Before
  public void markCharmLearnable() throws Exception {
    when(arbitrator.isLearnable(charm)).thenReturn(true);
  }

  @Before
  public void createCharacter() throws Exception {
    character.setCasteType(new DummyCasteType(element.name()));
    character.setCharacterType(CharacterType.DB);
  }

  @Test
  public void isLearnableIfCharacterIsDragonBloodOfSameElementalAspectAsEffect() throws Exception {
    effects.add(new ElementalSubeffect(element, character, condition));
    assertThat(condition.isFulfilled(), is(true));
  }
}