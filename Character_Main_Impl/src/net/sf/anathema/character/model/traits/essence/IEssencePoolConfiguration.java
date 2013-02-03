package net.sf.anathema.character.model.traits.essence;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface IEssencePoolConfiguration {

  String getPersonalPool();
  
  int getPersonalPoolValue();

  String getPeripheralPool();
  
  int getPeripheralPoolValue();
  
  int getOverdrivePoolValue();
  
  IdentifiedInteger[] getComplexPools();
  
  String getAttunedPool();
  
  int getAttunedPoolValue();

  boolean isEssenceUser();

  boolean hasPeripheralPool();

  void addPoolChangeListener(IChangeListener listener);
}