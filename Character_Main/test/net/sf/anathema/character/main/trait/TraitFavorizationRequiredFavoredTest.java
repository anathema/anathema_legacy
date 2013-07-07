package net.sf.anathema.character.main.trait;

import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.main.library.trait.favorable.FavorableState;
import net.sf.anathema.character.main.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.main.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.main.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.trait.DummyTrait;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TraitFavorizationRequiredFavoredTest {

  private DummyTrait trait;

  @Before
  public void setUp() throws Exception {
    this.trait = new DummyTrait(AbilityType.Performance);
  }

  private TraitFavorization createObjectUnderTest(boolean isRequiredFavored) {
    DummyHero dummyHero = new DummyHero();
    return new TraitFavorization(dummyHero, null, new FriendlyIncrementChecker(), trait, isRequiredFavored);
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

  @Test(expected = IllegalStateException.class)
  public void testSetCasteNotAllowedForRequiredFavored() throws Exception {
    DummyHero dummyHero = new DummyHero();
    final TraitFavorization favorization = new TraitFavorization(dummyHero, null, new GrumpyIncrementChecker(), trait, true);
    favorization.setCaste(true);
  }
}