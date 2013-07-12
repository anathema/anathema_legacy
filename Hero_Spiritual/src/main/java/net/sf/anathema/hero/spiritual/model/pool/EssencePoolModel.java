package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.character.main.essence.IEssencePoolModifier;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface EssencePoolModel {

  Identifier ID = new SimpleIdentifier("EssencePool");

  void addOverdrivePool(OverdrivePool pool);

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

  void addPoolChangeListener(ChangeListener listener);

  Iterable<IEssencePoolModifier> getEssencePoolModifiers();

  void addEssencePoolModifier(IEssencePoolModifier modifier);
}