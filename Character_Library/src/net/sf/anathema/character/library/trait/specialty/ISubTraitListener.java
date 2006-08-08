package net.sf.anathema.character.library.trait.specialty;

public interface ISubTraitListener {
  
  public void specialtyValueChanged();

  public void specialtyAdded(ISubTrait specialty);

  public void specialtyRemoved(ISubTrait specialty);
}