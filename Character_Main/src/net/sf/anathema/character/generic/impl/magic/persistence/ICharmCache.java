package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.util.Identified;

public interface ICharmCache extends IExtensibleDataSet {
  ICharm[] getCharms(Identified type);

  Identified[] getCharmTypes();

  ISpecialCharm[] getSpecialCharmData(Identified type);

  String getCharmRename(String name);
}