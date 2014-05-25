package net.sf.anathema.hero.traits.model;

public interface ITraitFavorization {

  void addFavorableStateChangedListener(IFavorableStateChangedListener listener);

  FavorableState getFavorableState();

  boolean isCaste();

  boolean isCasteOrFavored();

  boolean isFavored();

  void setFavorableState(FavorableState state);

  void setFavored(boolean favored);

  void updateFavorableStateToCaste();

  int getMinimalValue();
}