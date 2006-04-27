package net.sf.anathema.character.generic.magic.charms.type;

public interface ISimpleSpecialsModel extends ITypeSpecialsModel {

  public int getSpeed();

  public TurnType getTurnType();

  public int getDefenseModifier();
}