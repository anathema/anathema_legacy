package net.sf.anathema.test.character.library.trait;

import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.dummy.character.DummyBasicCharacterData;
import net.sf.anathema.dummy.character.trait.DummyDefaultTrait;
import net.sf.anathema.lib.testing.BasicTestCase;

public class TraitFavorizationRequiredFavoredTest extends BasicTestCase {

  private DummyDefaultTrait trait;

  @Override
  protected void setUp() throws Exception {
    this.trait = new DummyDefaultTrait(AbilityType.Performance);
  }

  private TraitFavorization createObjectUnderTest(boolean isRequiredFavored) {
    IBasicCharacterData characterData = new DummyBasicCharacterData();
    return new TraitFavorization(characterData, null, new FriendlyIncrementChecker(), trait, isRequiredFavored);
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
    IBasicCharacterData characterData = new DummyBasicCharacterData();
    final TraitFavorization favorization = new TraitFavorization(characterData, null, new GrumpyIncrementChecker(), trait, true);
    assertThrowsException(IllegalStateException.class, new ISimpleBlock() {
      public void execute() {
        favorization.setCaste(true);
      }
    });
  }
}