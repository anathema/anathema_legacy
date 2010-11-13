package net.sf.anathema.character.generic.magic.charms.type;

public interface ISimpleSpecialsModel extends ITypeSpecialsModel {
  public static final int DEFAULT_SPEED = 6;
  public static final int DEFAULT_DEFENSE_MODIFIER = -1;

  public int getSpeed();

  public TurnType getTurnType();

  public int getDefenseModifier();
}