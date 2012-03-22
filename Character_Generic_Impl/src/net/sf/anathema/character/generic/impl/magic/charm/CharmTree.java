package net.sf.anathema.character.generic.impl.magic.charm;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CharmTree implements ICharmTree {

  private final Map<String, ICharm> charmById = new HashMap<String, ICharm>();
  private ICharm[] allCharms;

  public CharmTree(ICharmTemplate charmTemplate) {
    this(charmTemplate.getCharms());
  }

  public CharmTree(ICharm[] charms) {
    this.allCharms = charms;
    for (ICharm charm : allCharms) {
      charmById.put(charm.getId(), charm);
    }
  }

  @Override
  public ICharm getCharmById(String charmID) {
    return charmById.get(charmID);
  }

  @Override
  public ICharm[] getAllCharms() {
    return allCharms;
  }

  private void addCharmGroupsFor(Collection<String> groupIds, List<ICharmGroup> charmGroups, ICharm[] charms) {
    for (ICharm charm : charms) {
      String groupId = charm.getGroupId();
      if (!groupIds.contains(groupId) && isLearnable(charm)) {
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

  @Override
  public final ICharmGroup[] getAllCharmGroups() {
    Set<String> charmGroupSet = new HashSet<String>();
    List<ICharmGroup> charmGroups = new ArrayList<ICharmGroup>();
    addCharmGroupsFor(charmGroupSet, charmGroups, getAllCharms());
    return charmGroups.toArray(new ICharmGroup[charmGroups.size()]);
  }

  @Override
  public final List<ICharm> getAllCharmsForGroup(String id) {
    List<ICharm> groupCharms = new ArrayList<ICharm>();
    for (ICharm charm : getAllCharms()) {
      if (charm.getGroupId().equals(id)) {
        groupCharms.add(charm);
      }
    }
    return groupCharms;
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    return true;
  }
}