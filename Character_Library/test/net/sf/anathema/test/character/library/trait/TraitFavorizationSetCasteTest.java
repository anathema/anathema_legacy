package net.sf.anathema.test.character.library.trait;

import net.sf.anathema.character.dummy.trait.DummyDefaultTrait;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.dummy.DummyBasicCharacterData;
import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class TraitFavorizationSetCasteTest {

  private ITrait archeryTrait = new DummyDefaultTrait(AbilityType.Archery);

  private TraitFavorization createFriendlyTraitFavorization() {
    return createTraitFavorization(new FriendlyIncrementChecker());
  }

  private TraitFavorization createTraitFavorization(IIncrementChecker incrementChecker) {
    IBasicCharacterData characterData = new DummyBasicCharacterData();
    return new TraitFavorization(characterData, new ICasteType[]{new DummyCasteType()},
      incrementChecker, archeryTrait, false);
  }

  @Test
  public void testCasteAfterSettingCasteOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  @Test
  public void testCasteAfterSettingCasteOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  @Test
  public void testCasteAfterSettingCasteOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  @Test
  public void testDefaultAfterSettingNotCasteOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.setCaste(false);
    assertSame(FavorableState.Default, favorization.getFavorableState());
  }

  @Test
  public void testDefaultAfterSettingNotCasteOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.setCaste(false);
    assertSame(FavorableState.Default, favorization.getFavorableState());
  }

  @Test
  public void testFavoredAfterSettingNotCasteOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
    favorization.setCaste(false);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
  }
}