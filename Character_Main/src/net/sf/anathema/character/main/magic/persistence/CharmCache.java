package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCache implements ICharmCache {

  MultiEntryMap<Identifier, ICharm> charmSets = new MultiEntryMap<>();
  Map<String, String> renameData = new HashMap<>();
  Map<Identifier, List<ISpecialCharm>> specialCharms = new HashMap<>();

  @Override
  public ICharm[] getCharms(Identifier type) {
    type = new SimpleIdentifier(type.getId());
    List<ICharm> charmList = charmSets.get(type);
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  public void addCharm(Identifier type, ICharm charm) {
    type = new SimpleIdentifier(type.getId());
    charmSets.replace(type, charm, charm);
  }

  public boolean isEmpty() {
    return charmSets.keySet().isEmpty();
  }

  public Iterable<ICharm> getCharms() {
    List<ICharm> allCharms = new ArrayList<>();
    for (Identifier type : charmSets.keySet()) {
      for (ICharm charm : charmSets.get(type)) {
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

  public void addCharmRenames(Map<String, String> mappings) {
    if (mappings == null) {
      return;
    }
    renameData.putAll(mappings);
  }

  @Override
  public String getCharmRename(String id) {
    String newId = id;
    do {
      id = newId;
      newId = renameData.get(id);
    } while (newId != null);
    return id;
  }

  @Override
  public Identifier[] getCharmTypes() {
    return charmSets.keySet().toArray(new Identifier[0]);
  }
}