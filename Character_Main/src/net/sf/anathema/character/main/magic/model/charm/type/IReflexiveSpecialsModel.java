package net.sf.anathema.character.main.magic.model.charm.type;

public interface IReflexiveSpecialsModel extends ITypeSpecialsModel {

  Integer getPrimaryStep();

  Integer getSecondaryStep();

  boolean isSplitEnabled();
}