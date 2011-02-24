package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.library.ITraitFavorization;

public class NullTraitFavorization implements ITraitFavorization {

  public void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    // nothing to do
  }

  public ICasteType[] getCastes() {
    return new ICasteType[] { ICasteType.NULL_CASTE_TYPE };
  }

  public FavorableState getFavorableState() {
    return FavorableState.Default;
  }

  public boolean isCaste() {
    return false;
  }

  public boolean isCasteOrFavored() {
    return false;
  }

  public boolean isFavored() {
    return false;
  }

  public void setFavorableState(FavorableState state) {
    // nothing to do
  }

  public void setFavored(boolean favored) {
    // nothing to do
  }

  public void updateFavorableStateToCaste() {
    // nothing to do
  }

  public int getMinimalValue() {
    return 0;
  }

  public void ensureMinimalValue() {
    // nothing to do
  }
}