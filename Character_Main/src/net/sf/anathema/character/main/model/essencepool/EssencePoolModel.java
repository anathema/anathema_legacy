package net.sf.anathema.character.main.model.essencepool;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface EssencePoolModel {

  Identifier ID = new SimpleIdentifier("EssencePool");

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