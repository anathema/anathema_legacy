package net.sf.anathema.character.generic.type;

import org.junit.Test;

import static java.util.Arrays.asList;
import static net.sf.anathema.character.generic.type.CharacterType.GHOST;
import static net.sf.anathema.character.generic.type.CharacterType.SPIRIT;
import static net.sf.anathema.character.generic.type.CharacterType.getAllExaltTypes;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class CharacterTypeTest {

  @Test
  public void doesNotIncludeSpiritsInListOfAllExalts() throws Exception {
    Iterable<ICharacterType> types = asList(getAllExaltTypes());
    assertThat(types, not(hasItem((ICharacterType) SPIRIT)));
  }

  @Test
  public void doesNotIncludeGhostsInListOfAllExalts() throws Exception {
    Iterable<ICharacterType> types = asList(getAllExaltTypes());
    assertThat(types, not(hasItem((ICharacterType) GHOST)));
  }

  @Test
  public void treatsAllExaltsAsEssenceUsers() throws Exception {
    assertThat(CharacterType.getAllEssenceUsers(), hasItems(getAllExaltTypes()));
  }

  @Test
  public void includesSpiritsAsEssenceUsers() throws Exception {
    assertThat(CharacterType.getAllEssenceUsers(), hasItem((ICharacterType) SPIRIT));
  }

  @Test
  public void includesGhostsAsEssenceUsers() throws Exception {
    assertThat(CharacterType.getAllEssenceUsers(), hasItem((ICharacterType) GHOST));
  }

  @Test
  public void treatsGhostsAsEssenceUsers() throws Exception {
    assertThat(CharacterType.GHOST.isEssenceUser(), is(true));
  }

  @Test
  public void treatsSpiritsAsEssenceUsers() throws Exception {
    assertThat(CharacterType.SPIRIT.isEssenceUser(), is(true));
  }
}
