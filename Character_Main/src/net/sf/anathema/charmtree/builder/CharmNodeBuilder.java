package net.sf.anathema.charmtree.builder;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.NodeFactory;

import java.util.Collection;
import java.util.Map;

public class CharmNodeBuilder {

  public static void buildNodes(Collection<ICharm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (ICharm charm : groupCharms) {
      IIdentifiedRegularNode node = NodeFactory.createChildlessNode(charm.getId());
      charmNodesById.put(charm.getId(), node);
    }
    for (ICharm charm : groupCharms) {
      for (ICharm parentCharm : charm.getRenderingPrerequisiteCharms()) {
        if (!groupCharms.contains(parentCharm)) {
          IIdentifiedRegularNode parentNode = charmNodesById.get(parentCharm.getId());
          if (parentNode == null) {
            parentNode = NodeFactory.createChildlessNode(parentCharm.getId());
            parentNode.setLowerToChildren(true);
            charmNodesById.put(parentCharm.getId(), parentNode);
          } else {
            parentNode.setLowerToChildren(false);
          }
        }
      }
    }
    for (ICharm charm : groupCharms) {
      for (IndirectCharmRequirement requirement : charm.getIndirectRequirements()) {
        String label = requirement.getStringRepresentation();
        IIdentifiedRegularNode parentNode = NodeFactory.createChildlessNode(label);
        charmNodesById.put(label, parentNode);
      }
    }
  }
}