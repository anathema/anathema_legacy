package net.sf.anathema.character.model.traits.essence;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface IEssencePoolConfiguration {

  public String getPersonalPool();
  
  public int getPersonalPoolValue();

  public String getPeripheralPool();
  
  public int getPeripheralPoolValue();
  
  public IdentifiedInteger[] getComplexPools();
  
  public String getAttunedPool();
  
  public int getAttunedPoolValue();

  public boolean isEssenceUser();

  public boolean hasPeripheralPool();

  public void addPoolChangeListener(IChangeListener listener);
}