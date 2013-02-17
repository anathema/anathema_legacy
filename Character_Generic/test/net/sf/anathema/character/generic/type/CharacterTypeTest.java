package net.sf.anathema.character.generic.type;

import org.junit.Test;

import static java.util.Arrays.asList;
import static net.sf.anathema.character.generic.type.CharacterTypes.getAllEssenceUsers;
import static net.sf.anathema.character.generic.type.CharacterTypes.getAllExaltTypes;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class CharacterTypeTest {

  @Test
  public void doesNotIncludeSpiritsInListOfAllExalts() throws Exception {
    Iterable<ICharacterType> types = asList(getAllExaltTypes());
    assertThat(types, not(hasItem((ICharacterType) new SpiritCharacterType())));
  }

  @Test
  public void doesNotIncludeGhostsInListOfAllExalts() throws Exception {
    Iterable<ICharacterType> types = asList(getAllExaltTypes());
    assertThat(types, not(hasItem((ICharacterType) new GhostCharacterType())));
  }

  @Test
  public void includesSpiritsAsEssenceUsers() throws Exception {
    assertThat(getAllEssenceUsers(), hasItem((ICharacterType) new SpiritCharacterType()));
  }

  @Test
  public void includesGhostsAsEssenceUsers() throws Exception {
    assertThat(getAllEssenceUsers(), hasItem((ICharacterType) new GhostCharacterType()));
  }

  @Test
  public void treatsGhostsAsEssenceUsers() throws Exception {
    assertThat(new GhostCharacterType().isEssenceUser(), is(true));
  }

  @Test
  public void treatsSpiritsAsEssenceUsers() throws Exception {
    assertThat(new SpiritCharacterType().isEssenceUser(), is(true));
  }
}
