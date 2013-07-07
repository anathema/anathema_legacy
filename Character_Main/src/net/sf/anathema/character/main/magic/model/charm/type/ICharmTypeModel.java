package net.sf.anathema.character.main.magic.model.charm.type;

public interface ICharmTypeModel {

  CharmType getCharmType();

  ITypeSpecialsModel getSpecialsModel();

  boolean hasSpecialsModel();
}