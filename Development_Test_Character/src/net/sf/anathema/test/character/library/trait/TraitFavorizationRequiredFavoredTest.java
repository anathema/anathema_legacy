package net.sf.anathema.test.character.library.trait;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.dummy.trait.DummyDefaultTrait;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.dummy.DummyBasicCharacterData;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;

import org.junit.Before;
import org.junit.Test;

public class TraitFavorizationRequiredFavoredTest {

  private DummyDefaultTrait trait;

  @Before
  public void setUp() throws Exception {
    this.trait = new DummyDefaultTrait(AbilityType.Performance);
  }

  private TraitFavorization createObjectUnderTest(boolean isRequiredFavored) {
    IBasicCharacterData characterData = new DummyBasicCharacterData();
    return new TraitFavorization(characterData, null, new FriendlyIncrementChecker(), trait, isRequiredFavored);
  }

  @Test
  public void testCreationWithIsRequiredFavoredTrue() throws Exception {
    TraitFavorization favorization = createObjectUnderTest(true);
    assertEquals(FavorableState.Favored, favorization.getFavorableState());
  }
  @Test
  public void testCreationWithIsRequiredFavoredFalse() throws Exception {
    TraitFavorization favorization = createObjectUnderTest(false);
    assertEquals(FavorableState.Default, favorization.getFavorableState());
  }
  @Test
  public void testSetFavoredFalseOnRequiredFavoredResultsInFavored() throws Exception {
    TraitFavorization favorization = createObjectUnderTest(true);
    favorization.setFavored(false);
    assertEquals(FavorableState.Favored, favorization.getFavorableState());
  }
  @Test(expected =IllegalStateException.class)
  public void testSetCasteNotAllowedForRequiredFavored() throws Exception {
    IBasicCharacterData characterData = new DummyBasicCharacterData();
    final TraitFavorization favorization = new TraitFavorization(characterData, null, new GrumpyIncrementChecker(), trait, true);
        favorization.setCaste(true);
  }
}