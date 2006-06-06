package net.sf.anathema.character.model.advance;

public interface IExperiencePointConfiguration {

  public IExperiencePointEntry[] getAllEntries();
  
  public IExperiencePointEntry addEntry();
  
  public void removeEntry(IExperiencePointEntry entry);
  
  public int getTotalExperiencePoints();
  
  public void addExperiencePointConfigurationListener(IExperiencePointConfigurationListener listener);

  public int getExtraSpendings();
}