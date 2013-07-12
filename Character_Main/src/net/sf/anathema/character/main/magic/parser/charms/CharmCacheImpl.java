package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCacheImpl implements CharmCache {

  MultiEntryMap<Identifier, Charm> charmSets = new MultiEntryMap<>();
  Map<Identifier, List<ISpecialCharm>> specialCharms = new HashMap<>();

  @Override
  public Charm[] getCharms(Identifier type) {
    type = new SimpleIdentifier(type.getId());
    List<Charm> charmList = charmSets.get(type);
    return charmList.toArray(new Charm[charmList.size()]);
  }

  public void addCharm(Identifier type, Charm charm) {
    type = new SimpleIdentifier(type.getId());
    charmSets.replace(type, charm, charm);
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

  private List<ISpecialCharm> getSpecialCharmList(Identifier type) {
    Map<Identifier, List<ISpecialCharm>> map = specialCharms;
    type = new SimpleIdentifier(type.getId());
    List<ISpecialCharm> list = map.get(type);
    if (list == null) {
      list = new ArrayList<>();
      map.put(type, list);
    }
    return list;
  }

  @Override
  public ISpecialCharm[] getSpecialCharmData(Identifier type) {
    List<ISpecialCharm> charmList = getSpecialCharmList(type);
    return charmList.toArray(new ISpecialCharm[charmList.size()]);
  }

  public void addSpecialCharmData(Identifier type, List<ISpecialCharm> data) {
    if (data == null) {
      return;
    }
    List<ISpecialCharm> list = getSpecialCharmList(type);
    list.addAll(data);
  }

  @Override
  public Identifier[] getCharmTypes() {
    return charmSets.keySet().toArray(new Identifier[0]);
  }
}