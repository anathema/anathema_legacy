package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface ICharmProvider {
	
  ISpecialCharm[] getSpecialMartialArtsCharms();

  ISpecialCharm[] getSpecialCharms(ICharacterType characterType);

  ISpecialCharm[] getSpecialCharms(ICharmLearnableArbitrator arbitrator, ICharmIdMap map, ICharacterType preferredCharacterType);

  void addMartialArtsSpecialCharm(ISpecialCharm charm);

  String getCharmRename(String name);
}