package net.sf.anathema.character.main.essencepool.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface EssencePoolStrategy {

  int getFullPersonalPool();

  int getFullPeripheralPool();

  int getExtendedPersonalPool();

  int getExtendedPeripheralPool();

  int getStandardPersonalPool();

  int getStandardPeripheralPool();

  int getUnmodifiedPersonalPool();

  int getUnmodifiedPeripheralPool();

  int getOverdrivePool();

  IdentifiedInteger[] getComplexPools();

  int getAttunementExpenditures();

  void addPoolChangeListener(IChangeListener listener);
}