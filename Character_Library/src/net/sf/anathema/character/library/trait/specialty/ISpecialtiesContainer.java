package net.sf.anathema.character.library.trait.specialty;

public interface ISpecialtiesContainer {

  public ISpecialty[] getSpecialties();

  public ISpecialty addSpecialty(String specialtyName);

  public void removeSpecialty(ISpecialty specialty);

  public void addSpecialtyListener(ISpecialtyListener listener);

  public void removeSpecialtyListener(ISpecialtyListener listener);

  public int getCreationSpecialtyCount();

  public int getCurrentSpecialtyCount();

  public int getExperienceLearnedSpecialtyCount();
}