package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.resources.IExtensibleDataSet;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmCache extends IExtensibleDataSet {
  ICharm[] getCharms(IIdentificate type);
  
  IIdentificate[] getCharmTypes();

  ISpecialCharm[] getSpecialCharmData(IIdentificate type);

  String getCharmRename(String name);
}