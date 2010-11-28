package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface ICharmProvider {

  public ISpecialCharm[] getSpecialCharms(ICharacterType characterType, IExaltedEdition edition);

  public ISpecialCharm[] getSpecialCharms(
      IExaltedEdition edition,
      ICharmLearnableArbitrator arbitrator,
      ICharmIdMap map,
      ICharacterType preferredCharacterType);

  public void addMartialArtsSpecialCharm(IExaltedEdition edition, ISpecialCharm charm);

  public void setSpecialCharms(ICharacterType type, IExaltedEdition edition, ISpecialCharm... charms);

}