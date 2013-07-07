package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.lib.util.Identifier;

public interface ICharmProvider {

  ISpecialCharm[] getSpecialCharms(Identifier type);

  ISpecialCharm[] getSpecialCharms(ICharmLearnableArbitrator arbitrator, CharmIdMap map, Identifier preferredType);

  String getCharmRename(String name);
}