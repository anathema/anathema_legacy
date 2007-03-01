package net.sf.anathema.character.generic.impl.magic.charm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;

public class CharmTree implements ICharmTree {

  private final Map<String, ICharm> charmById = new HashMap<String, ICharm>();
  private ICharm[] allCharms;

  public CharmTree(ICharmTemplate charmTemplate, IExaltedRuleSet rules) {
    this(charmTemplate.getCharms(rules));
  }

  public CharmTree(ICharm[] charms) {
    this.allCharms = charms;
    for (ICharm charm : allCharms) {
      charmById.put(charm.getId(), charm);
    }
  }

  public ICharm getCharmById(String charmID) {
    return charmById.get(charmID);
  }

  public ICharm[] getAllCharms() {
    return allCharms;
  }

  private final void addCharmGroupsFor(Collection<String> groupIds, List<ICharmGroup> charmGroups, ICharm[] charms) {
    for (ICharm charm : charms) {
      String groupId = charm.getGroupId();
      if (!groupIds.contains(groupId) && isLearnableCharm(charm)) {
        groupIds.add(groupId);
        List<ICharm> groupCharms = getAllCharmsForGroup(groupId);
        charmGroups.add(new CharmGroup(
            charm.getCharacterType(),
            groupId,
            groupCharms.toArray(new ICharm[groupCharms.size()]),
            MartialArtsUtilities.isMartialArtsCharm(charm)));
      }
    }
  }

  public final ICharmGroup[] getAllCharmGroups() {
    Set<String> charmGroupSet = new HashSet<String>();
    List<ICharmGroup> charmGroups = new ArrayList<ICharmGroup>();
    addCharmGroupsFor(charmGroupSet, charmGroups, getAllCharms());
    return charmGroups.toArray(new ICharmGroup[charmGroups.size()]);
  }

  public final List<ICharm> getAllCharmsForGroup(String id) {
    List<ICharm> groupCharms = new ArrayList<ICharm>();
    for (ICharm charm : getAllCharms()) {
      if (charm.getGroupId().equals(id)) {
        groupCharms.add(charm);
      }
    }
    return groupCharms;
  }

  public boolean isLearnableCharm(ICharm charm) {
    return true;
  }
}