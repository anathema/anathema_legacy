package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCacheImpl implements CharmCache {

  private MultiEntryMap<Identifier, Charm> charmSets = new MultiEntryMap<>();
  private Map<Identifier, List<SpecialCharmDto>> specialCharmsByType = new HashMap<>();
  private Map<String, Charm> charmsById = new HashMap<>();
  private CharmProvider charmProvider;
  private ReflectionSpecialCharmBuilder specialCharmBuilder;

  public CharmCacheImpl(ReflectionSpecialCharmBuilder builder) {
    this.specialCharmBuilder = builder;
  }

  @Override
  public Charm getCharmById(String charmId) {
    return charmsById.get(charmId);
  }

  @Override
  public Charm[] getCharms(Identifier type) {
    type = new SimpleIdentifier(type.getId());
    List<Charm> charmList = charmSets.get(type);
    return charmList.toArray(new Charm[charmList.size()]);
  }

  @Override
  public CharmProvider getCharmProvider() {
    if (charmProvider == null) {
      charmProvider = new CharmProviderImpl(this);
    }
    return charmProvider;
  }

  public void addCharm(Identifier type, Charm charm) {
    type = new SimpleIdentifier(type.getId());
    charmSets.replace(type, charm, charm);
    charmsById.put(charm.getId(), charm);
    if (charmProvider != null) {
      throw new IllegalStateException("Charms worked before compilation is complete.");
    }
  }

  public boolean isEmpty() {
    return charmSets.keySet().isEmpty();
  }

  public Iterable<Charm> getCharms() {
    List<Charm> allCharms = new ArrayList<>();
    for (Identifier type : charmSets.keySet()) {
      for (Charm charm : charmSets.get(type)) {
        allCharms.add(charm);
      }
    }
    return allCharms;
  }

  private List<SpecialCharmDto> getSpecialCharmList(Identifier type) {
    Map<Identifier, List<SpecialCharmDto>> map = specialCharmsByType;
    type = new SimpleIdentifier(type.getId());
    List<SpecialCharmDto> list = map.get(type);
    if (list == null) {
      list = new ArrayList<>();
      map.put(type, list);
    }
    return list;
  }

  @Override
  public ISpecialCharm[] getSpecialCharmData(Identifier type) {
    List<ISpecialCharm> specialCharms = new ArrayList<>();
    for (SpecialCharmDto dto : getSpecialCharmList(type)) {
      specialCharms.add(specialCharmBuilder.readCharm(dto));
    }
    return specialCharms.toArray(new ISpecialCharm[specialCharms.size()]);
  }

  public void addSpecialCharmData(Identifier type, List<SpecialCharmDto> data) {
    if (data == null) {
      return;
    }
    List<SpecialCharmDto> list = getSpecialCharmList(type);
    list.addAll(data);
  }

  @Override
  public Identifier[] getCharmTypes() {
    return charmSets.keySet().toArray(new Identifier[0]);
  }
}