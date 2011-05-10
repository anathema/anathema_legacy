package net.sf.anathema.character.model.traits.essence;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEssencePoolStrategy {

  public int getExtendedPersonalPool();

  public int getExtendedPeripheralPool();

  public int getStandardPersonalPool();

  public int getStandardPeripheralPool();
  
  public int getUnmodifiedPersonalPool();
  
  public int getUnmodifiedPeripheralPool();
  
  public int getAttunementExpenditures();

  public void addPoolChangeListener(IChangeListener listener);
}