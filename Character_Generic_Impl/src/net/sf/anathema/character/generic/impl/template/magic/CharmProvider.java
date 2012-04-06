package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.addAll;

public class CharmProvider implements ICharmProvider {

  private final Map<ICharacterType, ISpecialCharm[]> charmsByTypeByRuleSet = new HashMap<ICharacterType, ISpecialCharm[]>();
  private final Map<ICharacterType, Boolean> dataCharmsPrepared = new HashMap<ICharacterType, Boolean>();
  private final List<ISpecialCharm> martialArtsSpecialCharms = new ArrayList<ISpecialCharm>();
  private final CharmCache cache;

  public CharmProvider(CharmCache cache) {
    for (ICharacterType type : CharacterType.values()) {
      dataCharmsPrepared.put(type, false);
    }
    this.cache = cache;
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(ICharmLearnableArbitrator arbitrator, ICharmIdMap map, ICharacterType preferredCharacterType) {
    List<ISpecialCharm> relevantCharms = new ArrayList<ISpecialCharm>();
    ISpecialCharm[] allSpecialCharms = getAllSpecialCharms(preferredCharacterType);
    for (ISpecialCharm specialCharm : allSpecialCharms) {
      ICharm charm = map.getCharmById(specialCharm.getCharmId());
      if (charm != null && arbitrator.isLearnable(charm)) {
        relevantCharms.add(specialCharm);
      }
    }
    return relevantCharms.toArray(new ISpecialCharm[relevantCharms.size()]);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(ICharacterType characterType) {
    if (!dataCharmsPrepared.get(characterType)) {
      prepareDataCharms(characterType);
    }
    ISpecialCharm[] specialCharms = charmsByTypeByRuleSet.get(characterType);
    if (specialCharms == null) {
      specialCharms = new ISpecialCharm[0];
    }
    return specialCharms;
  }

  private ISpecialCharm[] getAllSpecialCharms(ICharacterType preferredCharacterType) {
    SpecialCharmSet set = new SpecialCharmSet();
    for (ICharacterType type : CharacterType.values()) {
      set.add(getSpecialCharms(type));
    }
    set.addAll(martialArtsSpecialCharms);
    for (ISpecialCharm preferredCharm : getSpecialCharms(preferredCharacterType)) {
      set.add(preferredCharm);
    }
    return set.toArray(new ISpecialCharm[set.size()]);
  }

  @Override
  public ISpecialCharm[] getSpecialMartialArtsCharms() {
    return martialArtsSpecialCharms.toArray(new ISpecialCharm[martialArtsSpecialCharms.size()]);
  }

  @Override
  public void addMartialArtsSpecialCharm(ISpecialCharm charm) {
    martialArtsSpecialCharms.add(charm);
  }

  @Override
  public String getCharmRename(String name) {
    return cache.getCharmRename(name);
  }

  private void prepareDataCharms(ICharacterType type) {
    List<ISpecialCharm> specialCharms = new ArrayList<ISpecialCharm>();
    ISpecialCharm[] base = charmsByTypeByRuleSet.get( type);
    if (base != null) {
      addAll(specialCharms, base);
    }
    addAll(specialCharms, cache.getSpecialCharmData(type));
    ISpecialCharm[] charmArray = new ISpecialCharm[specialCharms.size()];
    specialCharms.toArray(charmArray);
    dataCharmsPrepared.put(type, true);
    charmsByTypeByRuleSet.put(type, charmArray);
  }
}