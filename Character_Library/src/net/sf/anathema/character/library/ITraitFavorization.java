package net.sf.anathema.character.library;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;

public interface ITraitFavorization {

  public ICasteType< ? extends ICasteTypeVisitor> getCaste();

  public void addFavorableStateChangedListener(IFavorableStateChangedListener listener);

  public FavorableState getFavorableState();

  public boolean isCaste();

  public boolean isCasteOrFavored();

  public boolean isFavored();

  public void setFavorableState(FavorableState state);

  public void setFavored(boolean favored);

  public void updateFavorableStateToCaste();

  public int getMinimalValue();

  public void ensureMinimalValue();
}