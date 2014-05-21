package net.sf.anathema.hero.specialties;

public interface ISubTraitContainer {

  Specialty[] getSubTraits();

  Specialty addSubTrait(String subName);

  boolean isNewSubTraitAllowed();

  void removeSubTrait(Specialty specialty);

  void addSubTraitListener(ISpecialtyListener listener);

  void removeSubTraitListener(ISpecialtyListener listener);

  int getCreationDotTotal();

  int getCurrentDotTotal();

  int getExperienceDotTotal();

  boolean isRemovable(Specialty subTrait);

  void dispose();

  Specialty getSubTrait(String traitName);
}