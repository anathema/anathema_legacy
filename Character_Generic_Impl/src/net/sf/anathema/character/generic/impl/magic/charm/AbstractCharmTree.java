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

public abstract class AbstractCharmTree implements ICharmTree<ICharm> {

  private final Map<String, ICharm> charmById = new HashMap<String, ICharm>();
  private ICharm[] allCharms;

  public AbstractCharmTree(ICharmTemplate charmTemplate, IExaltedRuleSet rules) {
    this(charmTemplate.getCharms(rules));
  }

  public AbstractCharmTree(ICharm[] charms) {
    this.allCharms = charms;
    for (ICharm charm : allCharms) {
      charmById.put(charm.getId(), charm);
    }
  }

  public ICharm getCharmByID(String charmID) {
    return charmById.get(charmID);
  }

  public ICharm[] getAllCharms() {
    return allCharms;
  }

  protected final void addCharmGroupsFor(Collection<String> groupIds, List<ICharmGroup> charmGroups, ICharm[] charms) {
    for (ICharm charm : charms) {
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

  public final List<ICharm> getAllCharmsForGroup(String id) {
    List<ICharm> groupCharms = new ArrayList<ICharm>();
    for (ICharm charm : getAllCharms()) {
      if (charm.getGroupId().equals(id)) {
        groupCharms.add(charm);
      }
    }
    return groupCharms;
  }

  protected boolean isLearnableCharm(ICharm charm) {
    return true;
  }
}