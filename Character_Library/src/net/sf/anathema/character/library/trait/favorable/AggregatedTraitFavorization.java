package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
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

  public void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    favorization.addFavorableStateChangedListener(listener);
  }

  public ICasteType< ? extends ICasteTypeVisitor> getCaste() {
    return favorization.getCaste();
  }

  public FavorableState getFavorableState() {
    return favorization.getFavorableState();
  }

  public boolean isCaste() {
    return favorization.isCaste();
  }

  public boolean isCasteOrFavored() {
    return favorization.isCasteOrFavored();
  }

  public boolean isFavored() {
    return favorization.isFavored();
  }

  public void setFavorableState(FavorableState state) {
    //  nothing to do
  }

  public void setFavored(boolean favored) {
    //  nothing to do
  }

  public void updateFavorableStateToCaste() {
    //  nothing to do
  }

  public void ensureMinimalValue() {
    //  nothing to do 
  }
}