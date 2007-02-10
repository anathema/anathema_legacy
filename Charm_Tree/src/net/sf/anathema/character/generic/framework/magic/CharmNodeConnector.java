package net.sf.anathema.character.generic.framework.magic;

import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IIdentifiedRegularNode;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;

public class CharmNodeConnector {

  public static void connectNodes(List<ICharm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (ICharm charm : groupCharms) {
      IIdentifiedRegularNode childNode = charmNodesById.get(charm.getId());
      for (ICharm parentCharm : charm.getRenderingPrerequisiteCharms()) {
        IIdentifiedRegularNode parentNode = charmNodesById.get(parentCharm.getId());
        connectNodes(childNode, parentNode);
      }
      for (ICharmAttributeRequirement requirement : charm.getAttributeRequirements()) {
        String id = "Requirement." + requirement.getAttribute().getId() + "." + requirement.getCount(); //$NON-NLS-1$//$NON-NLS-2$
        IIdentifiedRegularNode parentNode = charmNodesById.get(id);
        connectNodes(childNode, parentNode);
      }
    }
  }

  private static void connectNodes(IIdentifiedRegularNode childNode, IIdentifiedRegularNode parentNode) {
    parentNode.addChild(childNode);
    childNode.addParent(parentNode);
  }
}