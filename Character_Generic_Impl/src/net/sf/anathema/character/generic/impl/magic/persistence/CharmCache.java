package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCache implements ICharmCache {
	
  MultiEntryMap<Identified, ICharm> charmSets = new MultiEntryMap<>();
  Map<String, String> renameData = new HashMap<>();
  Map<Identified, List<ISpecialCharm>> specialCharms = new HashMap<>();
  
  @Override
  public ICharm[] getCharms(Identified type) {
    type = new Identificate(type.getId());
    List<ICharm> charmList = charmSets.get(type);
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  public void addCharm(Identified type, ICharm charm) {
    type = new Identificate(type.getId());
    charmSets.replace(type, charm, charm);
  }

  public boolean isEmpty() {
    return charmSets.keySet().isEmpty();
  }

  public Iterable<ICharm> getCharms() {
    List<ICharm> allCharms = new ArrayList<>();
    for (Identified type : charmSets.keySet()) {
      for (ICharm charm : charmSets.get(type)) {
        allCharms.add(charm);
      }
    }
    return allCharms;
  }

  private List<ISpecialCharm> getSpecialCharmList(Identified type) {
    Map<Identified, List<ISpecialCharm>> map = specialCharms;
    type = new Identificate(type.getId());
    List<ISpecialCharm> list = map.get(type);
    if (list == null) {
      list = new ArrayList<>();
      map.put(type, list);
    }
    return list;
  }

  @Override
  public ISpecialCharm[] getSpecialCharmData(Identified type) {
    List<ISpecialCharm> charmList = getSpecialCharmList(type);
    return charmList.toArray(new ISpecialCharm[charmList.size()]);
  }

  public void addSpecialCharmData(Identified type, List<ISpecialCharm> data) {
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
  public Identified[] getCharmTypes() {
	return charmSets.keySet().toArray(new Identified[0]);
  }
}