package net.sf.anathema.character.model.background;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;

public interface IBackgroundConfiguration {

  void initStartingBackgrounds();

  IBackgroundTemplate[] getAllAvailableBackgroundTemplates();

  IBackground addBackground(String customBackgroundName, String description);

  IBackground addBackground(IBackgroundTemplate type, String description);

  IBackground addBackground(String customBackgroundName, String description, boolean loadIfExists);

  IBackground addBackground(IBackgroundTemplate type, String description, boolean loadIfExists);

  IBackground[] getBackgrounds();

  void addBackgroundListener(IBackgroundListener listener);

  void removeBackground(IBackground background);

  IBackground getBackgroundByTemplate(IBackgroundTemplate type);
}