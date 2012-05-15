package net.sf.anathema.character.library.trait.subtrait;

public interface ISubTraitContainer {

  ISubTrait[] getSubTraits();

  ISubTrait addSubTrait(String subName);
  
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