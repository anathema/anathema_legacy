package net.sf.anathema.charmtree.builder;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharmGraphNodeBuilder {
  public static Collection<IIdentifiedRegularNode> createNodesFromCharms(Collection<ICharm> groupCharms) {
    Map<String, IIdentifiedRegularNode> charmNodesById = new LinkedHashMap<>();
    CharmNodeBuilder.buildNodes(groupCharms, charmNodesById);
    CharmNodeConnector.connectNodes(groupCharms, charmNodesById);
    return charmNodesById.values();
  }
}