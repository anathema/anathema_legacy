package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface ICharmProvider {
	
  ISpecialCharm[] getSpecialMartialArtsCharms(IExaltedEdition edition);

  ISpecialCharm[] getSpecialCharms(ICharacterType characterType, IExaltedEdition edition);

  ISpecialCharm[] getSpecialCharms(
      IExaltedEdition edition,
      ICharmLearnableArbitrator arbitrator,
      ICharmIdMap map,
      ICharacterType preferredCharacterType);

  void addMartialArtsSpecialCharm(IExaltedEdition edition, ISpecialCharm charm);

  String getCharmRename(IExaltedRuleSet rules, String name);
}