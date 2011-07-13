package net.sf.anathema.character.model.traits.essence;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface IEssencePoolStrategy {

  public int getFullPersonalPool();

  public int getFullPeripheralPool();

  public int getExtendedPersonalPool();

  public int getExtendedPeripheralPool();

  public int getStandardPersonalPool();

  public int getStandardPeripheralPool();
  
  public int getUnmodifiedPersonalPool();
  
  public int getUnmodifiedPeripheralPool();
  
  public IdentifiedInteger[] getComplexPools();
  
  public int getAttunementExpenditures();

  public void addPoolChangeListener(IChangeListener listener);
}