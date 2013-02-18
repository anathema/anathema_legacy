package net.sf.anathema.character.generic.type;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class HardCodedCharacterTypesTest {

  private HardcodedCharacterTypes characterTypes= new HardcodedCharacterTypes();

  @Test
  public void doesNotIncludeSpiritsInListOfAllExalts() throws Exception {
    Iterable<ICharacterType> types = asList(characterTypes.getAllExaltTypes());
    assertThat(types, not(hasItem((ICharacterType) new SpiritCharacterType())));
  }

  @Test
  public void doesNotIncludeGhostsInListOfAllExalts() throws Exception {
    Iterable<ICharacterType> types = asList(characterTypes.getAllExaltTypes());
    assertThat(types, not(hasItem((ICharacterType) new GhostCharacterType())));
  }

  @Test
  public void includesSpiritsAsEssenceUsers() throws Exception {
    assertThat(characterTypes.getAllEssenceUsers(), hasItem((ICharacterType) new SpiritCharacterType()));
  }

  @Test
  public void includesGhostsAsEssenceUsers() throws Exception {
    assertThat(characterTypes.getAllEssenceUsers(), hasItem((ICharacterType) new GhostCharacterType()));
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