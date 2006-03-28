package net.sf.anathema.character.model.traits.essence;

import net.sf.anathema.lib.control.IChangeListener;

public interface IEssencePoolStrategy {

  public int getExtendedPersonalPool();

  public int getExtendedPeripheralPool();

  public int getStandardPersonalPool();

  public int getStandardPeripheralPool();

  public void addPoolChangeListener(IChangeListener listener);
}