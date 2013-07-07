package net.sf.anathema.character.main.trait;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.favorable.FavorableState;
import net.sf.anathema.character.main.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.main.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.main.testing.dummy.DummyCasteType;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.trait.DummyTrait;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class TraitFavorizationSetFavoredTest {

  private Trait archeryTrait = new DummyTrait(AbilityType.Archery);
  private IFavorableStateChangedListener listener = mock(IFavorableStateChangedListener.class);

  private TraitFavorization createFriendlyTraitFavorization() {
    return createTraitFavorization(new FriendlyIncrementChecker());
  }

  private TraitFavorization createTraitFavorization(IncrementChecker incrementChecker) {
    DummyHero dummyHero = new DummyHero();
    return new TraitFavorization(dummyHero, new CasteType[]{new DummyCasteType()}, incrementChecker, archeryTrait, false);
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