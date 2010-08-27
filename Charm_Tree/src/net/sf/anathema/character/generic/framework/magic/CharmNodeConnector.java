package net.sf.anathema.character.generic.framework.magic;

import java.util.Collection;
import java.util.Map;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;

public class CharmNodeConnector {

  public static void connectNodes(Collection<ICharm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (ICharm charm : groupCharms) {
      IIdentifiedRegularNode childNode = charmNodesById.get(charm.getId());
      for (ICharm parentCharm : charm.getRenderingPrerequisiteCharms()) {
        IIdentifiedRegularNode parentNode = charmNodesById.get(parentCharm.getId());
        connectNodes(childNode, parentNode);
      }
      for (ICharmAttributeRequirement requirement : charm.getAttributeRequirements()) {        
        IIdentifiedRegularNode parentNode = charmNodesById.get(requirement.getStringRepresentation());
        connectNodes(childNode, parentNode);
      }
    }
  }

  private static void connectNodes(IIdentifiedRegularNode childNode, IIdentifiedRegularNode parentNode) {
    parentNode.addChild(childNode);
    childNode.addParent(parentNode);
  }
}