package net.sf.anathema.character.view.advance;

public interface ExperienceConfigurationViewListener {

  void addRequested();

  void removeRequested(int index);

  void selectionChanged(int index);
}