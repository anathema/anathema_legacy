package net.sf.anathema.character.library.trait.subtrait;

public interface ISubTraitContainer {

  public ISubTrait[] getSubTraits();

  public ISubTrait addSubTrait(String subName);

  public void removeSubTrait(ISubTrait specialty);

  public void addSubTraitListener(ISubTraitListener listener);

  public void removeSubTraitListener(ISubTraitListener listener);

  public int getCreationDotTotal();

  public int getCurrentDotTotal();

  public int getExperienceDotTotal();

  public boolean isRemovable(ISubTrait subTrait);

  public void dispose();

  public ISubTrait getSubTrait(String traitName);
}