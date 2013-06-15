package net.sf.anathema.character.main.essencepool.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface EssencePoolModel {

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