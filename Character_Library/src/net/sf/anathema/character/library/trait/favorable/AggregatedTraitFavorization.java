package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;

public class AggregatedTraitFavorization implements ITraitFavorization {

  private final ISubTrait subTrait;
  private final ITraitFavorization favorization;
  private final ISubTraitContainer container;

  public AggregatedTraitFavorization(
      ITraitFavorization favorization,
      final ISubTrait subTrait,
      ISubTraitContainer container) {
    this.favorization = favorization;
    this.subTrait = subTrait;
    this.container = container;
  }

  @Override
  public int getMinimalValue() {
    if (getFavorableState() != FavorableState.Favored) {
      return 0;
    }
    for (ISubTrait trait : container.getSubTraits()) {
      if (subTrait == trait) {
        continue;
      }
      if (trait.getCurrentValue() >= 1) {
        return 0;
      }
    }
    return 1;
  }

  @Override
  public void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    favorization.addFavorableStateChangedListener(listener);
  }

  @Override
  public ICasteType[] getCastes() {
    return favorization.getCastes();
  }

  @Override
  public FavorableState getFavorableState() {
    return favorization.getFavorableState();
  }

  @Override
  public boolean isCaste() {
    return favorization.isCaste();
  }

  @Override
  public boolean isCasteOrFavored() {
    return favorization.isCasteOrFavored();
  }

  @Override
  public boolean isFavored() {
    return favorization.isFavored();
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
  public void ensureMinimalValue() {
    // nothing to do
  }
}