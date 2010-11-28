package net.sf.anathema.character.generic.framework.magic;

import java.util.Collection;
import java.util.Map;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.NodeFactory;

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
          }
          else {
            parentNode.setLowerToChildren(false);
          }
        }
      }
    }
    for (ICharm charm : groupCharms) {
      for (ICharmAttributeRequirement requirement : charm.getAttributeRequirements()) {
        String id = requirement.getStringRepresentation();
        IIdentifiedRegularNode parentNode = NodeFactory.createChildlessNode(id);
        charmNodesById.put(id, parentNode);
      }
    }
  }
}