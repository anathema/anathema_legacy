package net.sf.anathema.character.generic.framework.magic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class CharmGraphNodeProvider {

  private final MultiEntryMap<String, ICharm> charmsByGroupId = new MultiEntryMap<String, ICharm>();
  private final Set<String> groupIds = new HashSet<String>();

  public CharmGraphNodeProvider(ICharm[] charms) {
    for (ICharm charm : charms) {
      String groupId = charm.getGroupId();
      charmsByGroupId.add(groupId, charm);
      groupIds.add(groupId);
    }
  }

  public IIdentifiedRegularNode[] getNodesForGroup(String groupId) {
    List<ICharm> groupCharms = charmsByGroupId.get(groupId);
    return CharmGraphNodeBuilder.createNodesFromCharms(groupCharms);
  }

  public Set<String> getGroupIds() {
    return groupIds;
  }
}