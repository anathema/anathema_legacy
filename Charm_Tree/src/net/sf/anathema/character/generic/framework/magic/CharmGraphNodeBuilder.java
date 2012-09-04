package net.sf.anathema.character.generic.framework.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharmGraphNodeBuilder {
  public static IIdentifiedRegularNode[] createNodesFromCharms(Collection<ICharm> groupCharms) {
    Map<String, IIdentifiedRegularNode> charmNodesById = new LinkedHashMap<String, IIdentifiedRegularNode>();
    CharmNodeBuilder.buildNodes(groupCharms, charmNodesById);
    CharmNodeConnector.connectNodes(groupCharms, charmNodesById);
    Collection<IIdentifiedRegularNode> nodes = charmNodesById.values();
    return nodes.toArray(new IIdentifiedRegularNode[nodes.size()]);
  }
}