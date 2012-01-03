package net.sf.anathema.test.character.library.trait;

import net.sf.anathema.character.dummy.trait.DummyDefaultTrait;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.dummy.DummyBasicCharacterData;
import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.favorable.*;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class TraitFavorizationSetFavoredTest {

  private IDefaultTrait archeryTrait = new DummyDefaultTrait(AbilityType.Archery);
  private IFavorableStateChangedListener listener = mock(IFavorableStateChangedListener.class);

  private TraitFavorization createFriendlyTraitFavorization() {
    return createTraitFavorization(new FriendlyIncrementChecker());
  }

  private TraitFavorization createTraitFavorization(IIncrementChecker incrementChecker) {
    IBasicCharacterData characterData = new DummyBasicCharacterData();
    return new TraitFavorization(characterData, new ICasteType[]{new DummyCasteType()},
      incrementChecker, archeryTrait, false);
  }

  @Test
  public void testSetFavoredOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
  }

  @Test
  public void testSetFavoredOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.setFavored(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  @Test
  public void testSetNonFavoredOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.setFavored(false);
    assertSame(FavorableState.Default, favorization.getFavorableState());
  }

  @Test
  public void testSetNonFavoredOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
    favorization.setFavored(false);
    assertSame(FavorableState.Default, favorization.getFavorableState());
  }

  @Test
  public void testSetNonFavoredOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.setFavored(false);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  @Test
  public void testEventOnSetFavoredOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.addFavorableStateChangedListener(listener);
    favorization.setFavored(true);
    verify(listener).favorableStateChanged(FavorableState.Favored);
    verifyNoMoreInteractions(listener);
  }

  @Test
  public void testEventOnSetNotFavoredOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    favorization.addFavorableStateChangedListener(listener);
    favorization.setFavored(false);
    verify(listener).favorableStateChanged(FavorableState.Default);
    verifyNoMoreInteractions(listener);
  }

  @Test
  public void testNoEventOnSetFavoredOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
    favorization.addFavorableStateChangedListener(listener);
    favorization.setFavored(true);
    verifyZeroInteractions(listener);
  }

  @Test
  public void testNoEventOnSetFavoredOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.addFavorableStateChangedListener(listener);
    favorization.setFavored(true);
    verifyZeroInteractions(listener);
  }

  @Test
  public void testNoEventOnSetNotFavoredOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.addFavorableStateChangedListener(listener);
    favorization.setFavored(false);
    verifyZeroInteractions(listener);
  }

  @Test
  public void testNoEventOnSetNotFavoredOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.addFavorableStateChangedListener(listener);
    favorization.setFavored(false);
    verifyZeroInteractions(listener);
  }
}