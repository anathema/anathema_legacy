package net.sf.anathema.character.generic.magic.spells;

public interface ICircleTypeVisitor {

  public void visitTerrestrial(CircleType type);

  public void visitCelestial(CircleType type);

  public void visitSolar(CircleType type);

  public void visitShadowland(CircleType type);

  public void visitLabyrinth(CircleType type);

  public void visitVoid(CircleType type);
}