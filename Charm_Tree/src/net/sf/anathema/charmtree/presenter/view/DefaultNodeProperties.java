package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.framework.magic.MagicDisplayLabeler;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;

import static java.text.MessageFormat.format;
import static net.disy.commons.core.util.Ensure.ensureNotNull;
import static net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties.REQUIREMENT;

public class DefaultNodeProperties implements NodeProperties {

  private static final Logger logger = Logger.getLogger(DefaultNodeProperties.class);

  private final ICharmTreeViewProperties properties;
  private final MagicDisplayLabeler charmLabeler;
  private final IResources resources;
  private final ICharmIdMap map;

  public DefaultNodeProperties(IResources resources, ICharmTreeViewProperties properties, ICharmIdMap map) {
    this.properties = properties;
    this.resources = resources;
    this.map = map;
    this.charmLabeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public String getNodeText(String nodeId) {
    if (properties.isRequirementNode(nodeId)) {
      return textForRequirementNode(nodeId);
    }
    ICharm charm = findNonNullCharm(nodeId);
    String name = getNodeName(charm);
    if (charm.isTreeRoot()) {
      return name.toUpperCase();
    }
    return name;
  }

  private String textForRequirementNode(String nodeId) {
    String requirementWithCount = nodeId.replaceFirst(REQUIREMENT + ".", ""); //$NON-NLS-1$ //$NON-NLS-2$
    String[] strings = requirementWithCount.split("\\."); //$NON-NLS-1$
    int requirementCount = Integer.parseInt(strings[1]);
    String requirementName = resources.getString(REQUIREMENT + "." + strings[0]); //$NON-NLS-1$
    String charmString = resources.getString(
            requirementCount == 1 ? "Charms.Charm.Single" : "Charms.Charm.Multiple"); //$NON-NLS-1$//$NON-NLS-2$
    return resources.getString("Requirement.Message", requirementCount, requirementName, charmString); //$NON-NLS-1$
  }

  private String getNodeName(ICharm charm) {
    if (charmLabeler.supportsMagic(charm)) {
      return charmLabeler.getLabelForMagic(charm);
    }
    logger.warn(format("No resource key found for node {0}. It must be a requirement or a charm.", charm.getId()));
    return resources.getString(charm.getId());
  }

  private ICharm findNonNullCharm(final String charmId) {
    ICharm charm = map.getCharmById(charmId);
    ensureNotNull(format("No Charm with id ''{0}'' found.", charmId), charm);
    return charm;
  }
}