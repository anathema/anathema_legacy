package net.sf.anathema.character.model.background;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.library.trait.IModifiableTrait;

public interface IBackgroundConfiguration {

  public IBackgroundTemplate[] getAllAvailableBackgroundTemplates();

  public IModifiableTrait addBackground(String customBackgroundName);

  public IModifiableTrait addBackground(IBackgroundTemplate type);

  public IModifiableTrait[] getBackgrounds();

  public void addBackgroundListener(IBackgroundListener listener);

  public void removeBackground(IModifiableTrait background);

  public IModifiableTrait getBackgroundByTemplate(IBackgroundTemplate type);
}