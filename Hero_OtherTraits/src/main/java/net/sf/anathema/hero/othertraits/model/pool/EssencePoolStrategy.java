package net.sf.anathema.hero.othertraits.model.pool;

import net.sf.anathema.lib.control.ChangeListener;
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

  void addPoolChangeListener(ChangeListener listener);
}