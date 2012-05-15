package net.sf.anathema.character.library.trait.favorable;

public interface IFavorableStateVisitor {

  void visitDefault(FavorableState state);

  void visitFavored(FavorableState state);

  void visitCaste(FavorableState state);
}