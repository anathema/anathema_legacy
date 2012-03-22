package net.sf.anathema.character.generic.impl.template.magic;

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

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class CharmProvider implements ICharmProvider {

  private final Table<IExaltedEdition, ICharacterType, ISpecialCharm[]> charmsByTypeByRuleSet = new Table<IExaltedEdition, ICharacterType, ISpecialCharm[]>();
  private final Table<IExaltedEdition, ICharacterType, Boolean> dataCharmsPrepared = new Table<IExaltedEdition, ICharacterType, Boolean>();
  private final MultiEntryMap<IExaltedEdition, ISpecialCharm> martialArtsSpecialCharms = new MultiEntryMap<IExaltedEdition, ISpecialCharm>();
  private final CharmCache cache;

  public CharmProvider(CharmCache cache) {
    for (IExaltedEdition edition : ExaltedEdition.values())
      for (ICharacterType type : CharacterType.values())
        dataCharmsPrepared.add(edition, type, false);
    this.cache = cache;
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(IExaltedEdition edition, ICharmLearnableArbitrator arbitrator,
                                          ICharmIdMap map, ICharacterType preferredCharacterType) {
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

  @Override
  public ISpecialCharm[] getSpecialCharms(ICharacterType characterType, IExaltedEdition edition) {
    if (!dataCharmsPrepared.get(edition, characterType)) {
      prepareDataCharms(characterType, edition);
    }
    ISpecialCharm[] specialCharms = charmsByTypeByRuleSet.get(edition, characterType);
    if (specialCharms == null) {
      specialCharms = new ISpecialCharm[0];
    }
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

  @Override
  public ISpecialCharm[] getSpecialMartialArtsCharms(IExaltedEdition edition) {
    List<ISpecialCharm> specialCharms = martialArtsSpecialCharms.get(edition);
    return specialCharms.toArray(new ISpecialCharm[specialCharms.size()]);
  }

  @Override
  public void addMartialArtsSpecialCharm(IExaltedEdition edition, ISpecialCharm charm) {
    martialArtsSpecialCharms.add(edition, charm);
  }

  @Override
  public String getCharmRename(IExaltedRuleSet rules, String name) {
    return cache.getCharmRename(name);
  }

  private void prepareDataCharms(ICharacterType type, IExaltedEdition edition) {
    List<ISpecialCharm> specialCharms = new ArrayList<ISpecialCharm>();
    ISpecialCharm[] base = charmsByTypeByRuleSet.get(edition, type);
    if (base != null) {
      addAll(specialCharms, base);
    }
    addAll(specialCharms, cache.getSpecialCharmData(type));
    ISpecialCharm[] charmArray = new ISpecialCharm[specialCharms.size()];
    specialCharms.toArray(charmArray);
    dataCharmsPrepared.add(edition, type, true);
    charmsByTypeByRuleSet.add(edition, type, charmArray);
  }
}