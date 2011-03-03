package net.sf.anathema.character.model.background;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public interface IBackgroundConfiguration {

  public IBackgroundTemplate[] getAllAvailableBackgroundTemplates();
  
  public IDefaultTrait addBackground(String customBackgroundName);
  
  public IDefaultTrait addBackground(IBackgroundTemplate type);

  public IDefaultTrait addBackground(String customBackgroundName, boolean loadIfExists);

  public IDefaultTrait addBackground(IBackgroundTemplate type, boolean loadIfExists);

  public IDefaultTrait[] getBackgrounds();

  public void addBackgroundListener(IBackgroundListener listener);

  public void removeBackground(IDefaultTrait background);

  public IDefaultTrait getBackgroundByTemplate(IBackgroundTemplate type);
}