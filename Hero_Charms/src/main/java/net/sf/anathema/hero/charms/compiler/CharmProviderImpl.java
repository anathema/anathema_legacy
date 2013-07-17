package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmIdMap;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.charms.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmProviderImpl implements CharmProvider {

  private final Map<String, ISpecialCharm[]> specialCharmsByType = new HashMap<>();
  private final Map<String, Charm[]> charmsByType = new HashMap<>();

  public CharmProviderImpl(CharmCache cache) {
    for (Identifier type : cache.getCharmTypes()) {
      specialCharmsByType.put(type.getId(), cache.getSpecialCharmData(type));
      charmsByType.put(type.getId(), cache.getCharms(type));
    }
    charmsByType.put(MARTIAL_ARTS.getId(), cache.getCharms(MARTIAL_ARTS));
  }

  @Override
  public Charm[] getMartialArtsCharms() {
    return getCharms(MARTIAL_ARTS.getId());
  }

  @Override
  public Charm[] getCharms(CharacterType characterType) {
    return getCharms(characterType.getId());
  }

  private Charm[] getCharms(String id) {
    if (!charmsByType.containsKey(id)) {
      return new Charm[0];
    }
    return charmsByType.get(id);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(ICharmLearnableArbitrator arbitrator, CharmIdMap map, Identifier preferredType) {
    List<ISpecialCharm> relevantCharms = new ArrayList<>();
    ISpecialCharm[] allSpecialCharms = getAllSpecialCharms(preferredType);
    for (ISpecialCharm specialCharm : allSpecialCharms) {
      Charm charm = map.getCharmById(specialCharm.getCharmId());
      if (charm != null && arbitrator.isLearnable(charm)) {
        relevantCharms.add(specialCharm);
      }
    }
    return relevantCharms.toArray(new ISpecialCharm[relevantCharms.size()]);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms(Identifier type) {
    return getSpecialCharms(type.getId());
  }

  private ISpecialCharm[] getSpecialCharms(String id) {
    ISpecialCharm[] specialCharms = specialCharmsByType.get(id);
    if (specialCharms == null) {
      specialCharms = new ISpecialCharm[0];
    }
    return specialCharms;
  }

  private ISpecialCharm[] getAllSpecialCharms(Identifier preferredCharacterType) {
    SpecialCharmSet set = new SpecialCharmSet();
    for (String type : specialCharmsByType.keySet()) {
      set.add(getSpecialCharms(type));
    }
    for (ISpecialCharm preferredCharm : getSpecialCharms(preferredCharacterType)) {
      set.add(preferredCharm);
    }
    return set.toArray(new ISpecialCharm[set.size()]);
  }
}