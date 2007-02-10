package net.sf.anathema.character.generic.framework.magic;

import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IIdentifiedRegularNode;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.NodeFactory;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;

public class CharmNodeBuilder {

  public static void buildNodes(List<ICharm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
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
        String id = "Requirement." + requirement.getAttribute().getId() + "." + requirement.getCount(); //$NON-NLS-1$//$NON-NLS-2$
        IIdentifiedRegularNode parentNode = NodeFactory.createChildlessNode(id);
        charmNodesById.put(id, parentNode);
      }
    }
  }
}