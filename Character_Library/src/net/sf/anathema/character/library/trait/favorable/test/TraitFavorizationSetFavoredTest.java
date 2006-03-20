package net.sf.anathema.character.library.trait.favorable.test;

import net.sf.anathema.character.generic.framework.xml.magic.test.DummyCasteType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.IModifiableGenericTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.easymock.MockControl;

public class TraitFavorizationSetFavoredTest extends BasicTestCase {

  private IModifiableGenericTrait archeryTrait;
  private IFavorableStateChangedListener listener;
  private MockControl listenerControl;

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
    this.listenerControl = MockControl.createStrictControl(IFavorableStateChangedListener.class);
    this.listener = (IFavorableStateChangedListener) listenerControl.getMock();
  }

  public void testSetFavoredOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
  }

  public void testSetFavoredOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.setFavored(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  public void testSetNonFavoredOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.setFavored(false);
    assertSame(FavorableState.Default, favorization.getFavorableState());
  }

  public void testSetNonFavoredOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
    favorization.setFavored(false);
    assertSame(FavorableState.Default, favorization.getFavorableState());
  }

  public void testSetNonFavoredOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.setFavored(false);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
  }

  public void testEventOnSetFavoredOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.addFavorableStateChangedListener(listener);
    listener.favorableStateChanged(FavorableState.Favored);
    listenerControl.replay();
    favorization.setFavored(true);
    listenerControl.verify();
  }

  public void testEventOnSetNotFavoredOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    favorization.addFavorableStateChangedListener(listener);
    listener.favorableStateChanged(FavorableState.Default);
    listenerControl.replay();
    favorization.setFavored(false);
    listenerControl.verify();
  }

  public void testNoEventOnSetFavoredOnFavored() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setFavored(true);
    assertSame(FavorableState.Favored, favorization.getFavorableState());
    favorization.addFavorableStateChangedListener(listener);
    listenerControl.replay();
    favorization.setFavored(true);
    listenerControl.verify();
  }

  public void testNoEventOnSetFavoredOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.addFavorableStateChangedListener(listener);
    listenerControl.replay();
    favorization.setFavored(true);
    listenerControl.verify();
  }


  public void testNoEventOnSetNotFavoredOnDefault() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    assertSame(FavorableState.Default, favorization.getFavorableState());
    favorization.addFavorableStateChangedListener(listener);
    listenerControl.replay();
    favorization.setFavored(false);
    listenerControl.verify();
  }

  public void testNoEventOnSetNotFavoredOnCaste() throws Exception {
    TraitFavorization favorization = createFriendlyTraitFavorization();
    favorization.setCaste(true);
    assertSame(FavorableState.Caste, favorization.getFavorableState());
    favorization.addFavorableStateChangedListener(listener);
    listenerControl.replay();
    favorization.setFavored(false);
    listenerControl.verify();
  }
}