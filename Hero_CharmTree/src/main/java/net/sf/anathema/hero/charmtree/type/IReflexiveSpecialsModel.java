package net.sf.anathema.hero.charmtree.type;

public interface IReflexiveSpecialsModel extends ITypeSpecialsModel {

  Integer getPrimaryStep();

  Integer getSecondaryStep();

  boolean isSplitEnabled();
}