package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCache implements ICharmCache {
	
  MultiEntryMap<IIdentificate, ICharm> charmSets = new MultiEntryMap<IIdentificate, ICharm>();
  Map<String, String> renameData = new HashMap<String, String>();
  Map<IIdentificate, List<ISpecialCharm>> specialCharms = new HashMap<IIdentificate, List<ISpecialCharm>>();
  
  @Override
  public ICharm[] getCharms(IIdentificate type) {
    type = new Identificate(type.getId());
    List<ICharm> charmList = charmSets.get(type);
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  public void addCharm(IIdentificate type, ICharm charm) {
    type = new Identificate(type.getId());
    charmSets.replace(type, charm, charm);
  }

  public void addCharm(ICharmEntryData charmData) {
    ICharm charm = new Charm(charmData.getCoreData());
    addCharm(charm.getCharacterType(), charm);
  }

  public boolean isEmpty() {
    return charmSets.keySet().isEmpty();
  }

  public Iterable<ICharm> getCharms() {
    List<ICharm> allCharms = new ArrayList<ICharm>();
    for (IIdentificate type : charmSets.keySet()) {
      for (ICharm charm : charmSets.get(type)) {
        allCharms.add(charm);
      }
    }
    return allCharms;
  }

  private List<ISpecialCharm> getSpecialCharmList(IIdentificate type) {
    Map<IIdentificate, List<ISpecialCharm>> map = specialCharms;
    type = new Identificate(type.getId());
    List<ISpecialCharm> list = map.get(type);
    if (list == null) {
      list = new ArrayList<ISpecialCharm>();
      map.put(type, list);
    }
    return list;
  }

  @Override
  public ISpecialCharm[] getSpecialCharmData(IIdentificate type) {
    List<ISpecialCharm> charmList = getSpecialCharmList(type);
    return charmList.toArray(new ISpecialCharm[charmList.size()]);
  }

  public void addSpecialCharmData(IIdentificate type, List<ISpecialCharm> data) {
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
  public IIdentificate[] getCharmTypes() {
	return charmSets.keySet().toArray(new IIdentificate[0]);
  }
}