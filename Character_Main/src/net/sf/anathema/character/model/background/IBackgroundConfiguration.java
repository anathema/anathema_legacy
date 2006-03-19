package net.sf.anathema.character.model.background;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.library.trait.ITrait;

public interface IBackgroundConfiguration {

  public IBackgroundTemplate[] getAllAvailableBackgroundTemplates();

  public ITrait addBackground(String customBackgroundName);

  public ITrait addBackground(IBackgroundTemplate type);

  public ITrait[] getBackgrounds();

  public void addBackgroundListener(IBackgroundListener listener);

  public void removeBackground(ITrait background);

  public ITrait getBackgroundByTemplate(IBackgroundTemplate type);
}