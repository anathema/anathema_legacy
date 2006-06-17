package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.IModifiableGenericTrait;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class TraitFavorization implements ITraitFavorization {

  private FavorableState state;
  private final GenericControl<IFavorableStateChangedListener> favorableStateControl = new GenericControl<IFavorableStateChangedListener>();
  private final IIncrementChecker favoredIncrementChecker;
  private final IModifiableGenericTrait trait;
  private final ICasteType< ? extends ICasteTypeVisitor> caste;
  private final boolean isRequiredFavored;

  public TraitFavorization(
      ICasteType< ? extends ICasteTypeVisitor> caste,
      IIncrementChecker favoredIncrementChecker,
      IModifiableGenericTrait trait,
      boolean isRequiredFavored) {
    this.caste = caste;
    this.favoredIncrementChecker = favoredIncrementChecker;
    this.trait = trait;
    this.isRequiredFavored = isRequiredFavored;
    this.state = isRequiredFavored ? FavorableState.Favored : FavorableState.Default;
  }

  public final void setFavorableState(FavorableState state) {
    if (state == FavorableState.Caste && isRequiredFavored) {
      throw new IllegalStateException("Traits with required favored must not  be of any caste"); //$NON-NLS-1$
    }
    if (this.state == state) {
      return;
    }
    if (this.state == FavorableState.Caste && state == FavorableState.Favored) {
      return;
    }
    if (state == FavorableState.Favored && !favoredIncrementChecker.isValidIncrement(1)) {
      return;
    }
    if (isRequiredFavored && state == FavorableState.Default) {
      state = FavorableState.Favored;
    }
    this.state = state;
    if (this.state == FavorableState.Favored) {
      trait.setCurrentValue(Math.max(trait.getCurrentValue(), 1));
    }
    fireFavorableStateChangedEvent(state);
  }

  public void setFavored(boolean favored) {
    if (isCaste() || isFavored() == favored) {
      return;
    }
    setFavorableState(favored ? FavorableState.Favored : FavorableState.Default);
  }

  public void setCaste(boolean caste) {
    if (isCaste() == caste) {
      return;
    }
    setFavorableState(caste ? FavorableState.Caste : (isCaste() ? FavorableState.Default : FavorableState.Favored));
  }

  public final FavorableState getFavorableState() {
    return state;
  }

  public final void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    favorableStateControl.addListener(listener);
  }

  private final void fireFavorableStateChangedEvent(final FavorableState newState) {
    favorableStateControl.forAllDo(new IClosure<IFavorableStateChangedListener>() {
      public void execute(IFavorableStateChangedListener input) {
        input.favorableStateChanged(newState);
      }
    });
  }

  public final boolean isFavored() {
    return state == FavorableState.Favored;
  }

  public final boolean isCaste() {
    return state == FavorableState.Caste;
  }

  public final boolean isCasteOrFavored() {
    return isCaste() || isFavored();
  }

  public ICasteType< ? extends ICasteTypeVisitor> getCaste() {
    return caste;
  }
}
