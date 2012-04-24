package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmProvider {

  ISpecialCharm[] getSpecialCharms(IIdentificate type);

  ISpecialCharm[] getSpecialCharms(ICharmLearnableArbitrator arbitrator, ICharmIdMap map, IIdentificate preferredType);

  String getCharmRename(String name);
}