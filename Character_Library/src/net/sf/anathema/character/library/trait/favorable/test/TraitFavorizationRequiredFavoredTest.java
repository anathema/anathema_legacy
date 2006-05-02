package net.sf.anathema.character.library.trait.favorable.test;

import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.lib.testing.BasicTestCase;

public class TraitFavorizationRequiredFavoredTest extends BasicTestCase {

  private DummyModifiableGenericTrait trait;

  @Override
  protected void setUp() throws Exception {
    this.trait = new DummyModifiableGenericTrait(AbilityType.Performance);
  }

  private TraitFavorization createObjectUnderTest(boolean isRequiredFavored) {
    return new TraitFavorization(null, new FriendlyIncrementChecker(), trait, isRequiredFavored);
  }

  public void testCreationWithIsRequiredFavoredTrue() throws Exception {
    TraitFavorization favorization = createObjectUnderTest(true);
    assertEquals(FavorableState.Favored, favorization.getFavorableState());
  }

  public void testCreationWithIsRequiredFavoredFalse() throws Exception {
    TraitFavorization favorization = createObjectUnderTest(false);
    assertEquals(FavorableState.Default, favorization.getFavorableState());
  }

  public void testSetFavoredFalseOnRequiredFavoredResultsInFavored() throws Exception {
    TraitFavorization favorization = createObjectUnderTest(true);
    favorization.setFavored(false);
    assertEquals(FavorableState.Favored, favorization.getFavorableState());
  }

  public void testSetCasteNotAllowedForRequiredFavored() throws Exception {
    final TraitFavorization favorization = new TraitFavorization(null, new GrumpyIncrementChecker(), trait, true);
    assertThrowsException(IllegalStateException.class, new ISimpleBlock() {
      public void execute() {
        favorization.setCaste(true);
      }
    });
  }
}