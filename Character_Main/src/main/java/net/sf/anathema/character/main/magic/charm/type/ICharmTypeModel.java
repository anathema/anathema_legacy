package net.sf.anathema.character.main.magic.charm.type;

public interface ICharmTypeModel {

  CharmType getCharmType();

  ITypeSpecialsModel getSpecialsModel();

  boolean hasSpecialsModel();
}