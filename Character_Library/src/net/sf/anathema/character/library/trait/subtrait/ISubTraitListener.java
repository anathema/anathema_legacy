package net.sf.anathema.character.library.trait.subtrait;

public interface ISubTraitListener {
  
  public void specialtyValueChanged();

  public void specialtyAdded(ISubTrait specialty);

  public void specialtyRemoved(ISubTrait specialty);
}