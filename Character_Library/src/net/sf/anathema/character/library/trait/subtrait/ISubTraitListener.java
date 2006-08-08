package net.sf.anathema.character.library.trait.subtrait;

public interface ISubTraitListener {
  
  public void subTraitValueChanged();

  public void subTraitAdded(ISubTrait subTrait);

  public void subTraitRemoved(ISubTrait subTrait);
}