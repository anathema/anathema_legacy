package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmCache {
  ICharm[] getCharms(IIdentificate type);

  ISpecialCharm[] getSpecialCharmData(IIdentificate type);

  String getCharmRename(String name);
}