package net.sf.anathema.character.view.advance;

public interface IExperienceConfigurationViewListener {

  public void addRequested();

  public void removeRequested(int index);

  public void selectionChanged(int index);
}