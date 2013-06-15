package net.sf.anathema.character.generic.magic.spells;

public interface ICircleTypeVisitor {

  void visitTerrestrial(CircleType type);

  void visitCelestial(CircleType type);

  void visitSolar(CircleType type);

  void visitShadowland(CircleType type);

  void visitLabyrinth(CircleType type);

  void visitVoid(CircleType type);
}