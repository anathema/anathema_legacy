package net.sf.anathema.hero.charms.display.node;

import java.util.Collection;
import java.util.Map;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;

public class CharmNodeConnector {

  public static void connectNodes(Collection<Charm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (Charm charm : groupCharms) {
      IIdentifiedRegularNode childNode = charmNodesById.get(charm.getId());
      for (Charm parentCharm : charm.getRenderingPrerequisiteCharms()) {
        IIdentifiedRegularNode parentNode = charmNodesById.get(parentCharm.getId());
        connectNodes(childNode, parentNode);
      }
      for (IndirectCharmLearnPrerequisite requirement : charm.getPrerequisitesOfType(IndirectCharmLearnPrerequisite.class)) {
        IIdentifiedRegularNode parentNode = charmNodesById.get(requirement.getStringLabel());
        connectNodes(childNode, parentNode);
      }
    }
  }

  private static void connectNodes(IIdentifiedRegularNode childNode, IIdentifiedRegularNode parentNode) {
    parentNode.addChild(childNode);
    childNode.addParent(parentNode);
  }
}