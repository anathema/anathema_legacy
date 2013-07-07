package net.sf.anathema.character.main.magic.charms.type;

public interface ICharmTypeModel {

  CharmType getCharmType();

  ITypeSpecialsModel getSpecialsModel();

  boolean hasSpecialsModel();
}