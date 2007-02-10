package net.sf.anathema.character.generic.framework.magic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.platform.svgtree.graph.nodes.IIdentifiedRegularNode;

public class CharmGraphNodeBuilder {
  public static IIdentifiedRegularNode[] createNodesFromCharms(List<ICharm> groupCharms) {
    Map<String, IIdentifiedRegularNode> charmNodesById = new LinkedHashMap<String, IIdentifiedRegularNode>();
    CharmNodeBuilder.buildNodes(groupCharms, charmNodesById);
    CharmNodeConnector.connectNodes(groupCharms, charmNodesById);
    return charmNodesById.values().toArray(new IIdentifiedRegularNode[0]);
  }
}