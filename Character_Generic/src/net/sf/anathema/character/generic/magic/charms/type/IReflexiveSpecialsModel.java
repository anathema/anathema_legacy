package net.sf.anathema.character.generic.magic.charms.type;

public interface IReflexiveSpecialsModel extends ITypeSpecialsModel {

  Integer getPrimaryStep();

  Integer getSecondaryStep();

  boolean isSplitEnabled();
}