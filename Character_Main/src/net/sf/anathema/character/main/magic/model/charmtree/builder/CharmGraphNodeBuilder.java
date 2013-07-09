package net.sf.anathema.character.main.magic.model.charmtree.builder;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharmGraphNodeBuilder {
  public static Collection<IIdentifiedRegularNode> createNodesFromCharms(Collection<Charm> groupCharms) {
    Map<String, IIdentifiedRegularNode> charmNodesById = new LinkedHashMap<>();
    CharmNodeBuilder.buildNodes(groupCharms, charmNodesById);
    CharmNodeConnector.connectNodes(groupCharms, charmNodesById);
    return charmNodesById.values();
  }
}