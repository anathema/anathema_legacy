package net.sf.anathema.character.magic.charm.type;

public interface ISimpleSpecialsModel extends ITypeSpecialsModel {
  int DEFAULT_SPEED = 6;
  int DEFAULT_DEFENSE_MODIFIER = -1;

  int getSpeed();

  TurnType getTurnType();

  int getDefenseModifier();
}