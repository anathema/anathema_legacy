package net.sf.anathema.character.library.trait.subtrait;

import net.sf.anathema.character.library.trait.specialties.ISpecialty;

public interface ISubTraitContainer {

  ISpecialty[] getSubTraits();

  ISpecialty addSubTrait(String subName);

  boolean isNewSubTraitAllowed();

  void removeSubTrait(ISubTrait specialty);

  void addSubTraitListener(ISubTraitListener listener);

  void removeSubTraitListener(ISubTraitListener listener);

  int getCreationDotTotal();

  int getCurrentDotTotal();

  int getExperienceDotTotal();

  boolean isRemovable(ISubTrait subTrait);

  void dispose();

  ISubTrait getSubTrait(String traitName);
}