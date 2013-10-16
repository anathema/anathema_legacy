package net.sf.anathema.hero.charms.display.node;

import java.util.Collection;
import java.util.Map;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.NodeFactory;

public class CharmNodeBuilder {

  public static void buildNodes(Collection<Charm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (Charm charm : groupCharms) {
      IIdentifiedRegularNode node = NodeFactory.createChildlessNode(charm.getId());
      charmNodesById.put(charm.getId(), node);
    }
    for (Charm charm : groupCharms) {
      for (Charm parentCharm : charm.getRenderingPrerequisiteCharms()) {
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
    for (Charm charm : groupCharms) {
      for (IndirectCharmLearnPrerequisite prerequisite : charm.getPrerequisitesOfType(IndirectCharmLearnPrerequisite.class)) {
        String label = prerequisite.getRequirementLabel();
        IIdentifiedRegularNode parentNode = NodeFactory.createChildlessNode(label);
        charmNodesById.put(label, parentNode);
      }
    }
  }
}