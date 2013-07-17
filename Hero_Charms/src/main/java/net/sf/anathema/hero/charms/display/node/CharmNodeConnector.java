package net.sf.anathema.hero.charms.display.node;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.requirements.IndirectCharmRequirement;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;

import java.util.Collection;
import java.util.Map;

public class CharmNodeConnector {

  public static void connectNodes(Collection<Charm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (Charm charm : groupCharms) {
      IIdentifiedRegularNode childNode = charmNodesById.get(charm.getId());
      for (Charm parentCharm : charm.getRenderingPrerequisiteCharms()) {
        IIdentifiedRegularNode parentNode = charmNodesById.get(parentCharm.getId());
        connectNodes(childNode, parentNode);
      }
      for (IndirectCharmRequirement requirement : charm.getIndirectRequirements()) {
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