package net.sf.anathema.character.view.advance;

public interface IExperienceConfigurationViewListener {

  void addRequested();

  void removeRequested(int index);

  void selectionChanged(int index);
}