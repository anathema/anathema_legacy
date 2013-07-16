package net.sf.anathema.hero.charmtree.type;

public interface ICharmTypeModel {

  CharmType getCharmType();

  ITypeSpecialsModel getSpecialsModel();

  boolean hasSpecialsModel();
}