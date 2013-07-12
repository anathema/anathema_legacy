package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.parser.charms.CharmCache;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmProviderImpl implements CharmProvider {

  private final Map<Identifier, ISpecialCharm[]> charmsByType = new HashMap<>();
  private CharmCache cache;

  public CharmProviderImpl(CharmCache cache) {
    this.cache = cache;
    for (Identifier type : cache.getCharmTypes()) {
      charmsByType.put(type, cache.getSpecialCharmData(type));
    }
  }

  @Override
  public Charm[] getMartialArtsCharms() {
    return cache.getCharms(MARTIAL_ARTS);
  }

  @Override
  public Charm[] getCharms(CharacterType characterType) {
    return cache.getCharms(characterType);
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
    ISpecialCharm[] specialCharms = charmsByType.get(new SimpleIdentifier(type.getId()));
    if (specialCharms == null) {
      specialCharms = new ISpecialCharm[0];
    }
    return specialCharms;
  }

  private ISpecialCharm[] getAllSpecialCharms(Identifier preferredCharacterType) {
    SpecialCharmSet set = new SpecialCharmSet();
    for (Identifier type : charmsByType.keySet()) {
      set.add(getSpecialCharms(type));
    }
    for (ISpecialCharm preferredCharm : getSpecialCharms(preferredCharacterType)) {
      set.add(preferredCharm);
    }
    return set.toArray(new ISpecialCharm[set.size()]);
  }
}