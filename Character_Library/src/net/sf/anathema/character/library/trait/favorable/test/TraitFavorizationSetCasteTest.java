package net.sf.anathema.character.library.trait.favorable.test;

import net.sf.anathema.character.generic.framework.xml.magic.test.DummyCasteType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.IModifiableGenericTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.lib.testing.BasicTestCase;

public class TraitFavorizationSetCasteTest extends BasicTestCase {

  private IModifiableGenericTrait archeryTrait;

  private TraitFavorization createFriendlyTraitFavorization() {
    return createTraitFavorization(new FriendlyIncrementChecker());
  }

  private TraitFavorization createTraitFavorization(IIncrementChecker incrementChecker) {
    return new TraitFavorization(new DummyCasteType(), incrementChecker, archeryTrait, false);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.archeryTrait = new DummyModifiableGenericTrait(AbilityType.Archery);
  }

  public void testCasteAfterSettingCasteOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  public void testCasteAfterSettingCasteOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  public void testCasteAfterSettingCasteOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  public void testDefaultAfterSettingNotCasteOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.setCaste(false);
    assertSame(FavorableState.Default, favorization.getFavorableState());
  }

  public void testDefaultAfterSettingNotCasteOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.setCaste(false);
    assertSame(FavorableState.Default, favorization.getFavorableState());
  }

  public void testFavoredAfterSettingNotCasteOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
    favorization.setCaste(false);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
  }
}