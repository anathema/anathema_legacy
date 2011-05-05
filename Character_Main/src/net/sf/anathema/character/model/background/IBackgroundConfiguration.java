package net.sf.anathema.character.model.background;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;

public interface IBackgroundConfiguration {
	
  public void initStartingBackgrounds();

  public IBackgroundTemplate[] getAllAvailableBackgroundTemplates();
  
  public IBackground addBackground(String customBackgroundName, String description);
  
  public IBackground addBackground(IBackgroundTemplate type, String description);

  public IBackground addBackground(String customBackgroundName, String description, boolean loadIfExists);

  public IBackground addBackground(IBackgroundTemplate type, String description, boolean loadIfExists);

  public IBackground[] getBackgrounds();

  public void addBackgroundListener(IBackgroundListener listener);

  public void removeBackground(IBackground background);

  public IBackground getBackgroundByTemplate(IBackgroundTemplate type);
}