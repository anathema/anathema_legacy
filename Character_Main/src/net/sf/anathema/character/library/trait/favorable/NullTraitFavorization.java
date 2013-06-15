package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.library.ITraitFavorization;

public class NullTraitFavorization implements ITraitFavorization {

  @Override
  public void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    // nothing to do
  }

  @Override
  public ICasteType[] getCastes() {
    return new ICasteType[]{ICasteType.NULL_CASTE_TYPE};
  }

  @Override
  public FavorableState getFavorableState() {
    return FavorableState.Default;
  }

  @Override
  public boolean isCaste() {
    return false;
  }

  @Override
  public boolean isCasteOrFavored() {
    return false;
  }

  @Override
  public boolean isFavored() {
    return false;
  }

  @Override
  public void setFavorableState(FavorableState state) {
    // nothing to do
  }

  @Override
  public void setFavored(boolean favored) {
    // nothing to do
  }

  @Override
  public void updateFavorableStateToCaste() {
    // nothing to do
  }

  @Override
  public int getMinimalValue() {
    return 0;
  }

  @Override
  public void ensureMinimalValue() {
    // nothing to do
  }
}