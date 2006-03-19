package net.sf.anathema.character.library.trait.favorable;

public interface IFavorableStateVisitor {

  public void visitDefault(FavorableState state);

  public void visitFavored(FavorableState state);

  public void visitCaste(FavorableState state);
}