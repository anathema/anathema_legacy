package net.sf.anathema.hero.experience.display;

public interface ExperienceConfigurationViewListener {

  void addRequested();

  void removeRequested(int index);

  void selectionChanged(int index);
}