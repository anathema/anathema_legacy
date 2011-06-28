package net.sf.anathema.character.generic.impl.template.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.collection.Table;

public class CharmProvider implements ICharmProvider {

  private final Table<IExaltedEdition, ICharacterType, ISpecialCharm[]> charmsByTypeByRuleSet = new Table<IExaltedEdition, ICharacterType, ISpecialCharm[]>();
  private final Table<IExaltedEdition, ICharacterType, Boolean> dataCharmsPrepared = new Table<IExaltedEdition, ICharacterType, Boolean>();
  private final MultiEntryMap<IExaltedEdition, ISpecialCharm> martialArtsSpecialCharms = new MultiEntryMap<IExaltedEdition, ISpecialCharm>();
  
  public CharmProvider()
  {
	  for (IExaltedEdition edition : ExaltedEdition.values())
		  for (ICharacterType type : CharacterType.values())
			  dataCharmsPrepared.add(edition, type, false);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(
      IExaltedEdition edition,
      ICharmLearnableArbitrator arbitrator,
      ICharmIdMap map,
      ICharacterType preferredCharacterType) {
    List<ISpecialCharm> relevantCharms = new ArrayList<ISpecialCharm>();
    ISpecialCharm[] allSpecialCharms = getAllSpecialCharms(edition, preferredCharacterType);
    for (ISpecialCharm specialCharm : allSpecialCharms) {
      ICharm charm = map.getCharmById(specialCharm.getCharmId());
      if (charm != null && arbitrator.isLearnable(charm)) {
        relevantCharms.add(specialCharm);
      }
    }
    return relevantCharms.toArray(new ISpecialCharm[relevantCharms.size()]);
  }

  public ISpecialCharm[] getSpecialCharms(ICharacterType characterType, IExaltedEdition edition) {
	if (!dataCharmsPrepared.get(edition, characterType))
		prepareDataCharms(characterType, edition);
    ISpecialCharm[] specialCharms = charmsByTypeByRuleSet.get(edition, characterType);
    if (specialCharms == null)
      specialCharms = new ISpecialCharm[0];
    return specialCharms;
  }

  private ISpecialCharm[] getAllSpecialCharms(IExaltedEdition edition, ICharacterType preferredCharacterType) {
    SpecialCharmSet set = new SpecialCharmSet();
    for (ICharacterType type : CharacterType.values()) {
      set.add(getSpecialCharms(type, edition));
    }
    set.addAll(martialArtsSpecialCharms.get(edition));
    for (ISpecialCharm preferredCharm : getSpecialCharms(preferredCharacterType, edition)) {
      set.add(preferredCharm);
    }
    return set.toArray(new ISpecialCharm[set.size()]);
  }

  public void setSpecialCharms(ICharacterType type, IExaltedEdition edition, ISpecialCharm... charms)
  {
    charmsByTypeByRuleSet.add(edition, type, charms);
  }

  public void addMartialArtsSpecialCharm(IExaltedEdition edition, ISpecialCharm charm) {
    martialArtsSpecialCharms.add(edition, charm);
  }
  
  public String getCharmRename(IExaltedRuleSet rules, String name)
  {
	  return CharmCache.getInstance().getCharmRename(rules, name);
  }
  
  private void prepareDataCharms(ICharacterType type, IExaltedEdition edition)
  {
	  List<ISpecialCharm> specialCharms = new ArrayList<ISpecialCharm>();
	  ISpecialCharm[] base = charmsByTypeByRuleSet.get(edition, type);
	  if (base != null)
		  for (ISpecialCharm charm : base)
			specialCharms.add(charm);
	  for (ISpecialCharm charm : CharmCache.getInstance().getSpecialCharmData(type, edition.getDefaultRuleset()))
		specialCharms.add(charm);
	  ISpecialCharm[] charmArray = new ISpecialCharm[specialCharms.size()];
	  specialCharms.toArray(charmArray);
	  dataCharmsPrepared.add(edition, type, true);
	  charmsByTypeByRuleSet.add(edition, type, charmArray);
  }
}