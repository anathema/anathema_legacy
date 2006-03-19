package net.sf.anathema.character.library.trait.specialty;

public interface ISpecialtyListener {
  
  public void specialtyValueChanged();

  public void specialtyAdded(ISpecialty specialty);

  public void specialtyRemoved(ISpecialty specialty);
}