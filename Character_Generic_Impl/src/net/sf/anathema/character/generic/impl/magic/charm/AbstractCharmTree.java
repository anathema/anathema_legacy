package net.sf.anathema.character.generic.impl.magic.charm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;

public abstract class AbstractCharmTree<C extends ICharm> implements ICharmTree<C> {

  protected final void addCharmGroupsFor(Collection<String> groupIds, List<ICharmGroup> charmGroups, C[] charms) {
    for (C charm : charms) {
      String groupId = charm.getGroupId();
      if (!groupIds.contains(groupId) && isLearnableCharm(charm)) {
        groupIds.add(groupId);
        charmGroups.add(new CharmGroup(
            charm.getCharacterType(),
            groupId,
            getAllCharmsForGroup(groupId),
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

  public final List<C> getAllCharmsForGroup(String id) {
    List<C> groupCharms = new ArrayList<C>();
    for (C charm : getAllCharms()) {
      if (charm.getGroupId().equals(id)) {
        groupCharms.add(charm);
      }
    }
    return groupCharms;
  }

  protected abstract boolean isLearnableCharm(C charm);
}