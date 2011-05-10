package net.sf.anathema.character.model.traits.essence;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEssencePoolConfiguration {

  public String getPersonalPool();

  public String getPeripheralPool();
  
  public String getAttunedPool();

  public boolean isEssenceUser();

  public boolean hasPeripheralPool();

  public void addPoolChangeListener(IChangeListener listener);
}