package net.sf.anathema.character.model.advance;

public interface IExperiencePointConfigurationListener {

  public void entryAdded(IExperiencePointEntry entry);

  public void entryRemoved(IExperiencePointEntry entry);

  public void entryChanged(IExperiencePointEntry entry);
}