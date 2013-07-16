package net.sf.anathema.hero.charms.display.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.display.NodeProperties;

import static java.text.MessageFormat.format;

public class DefaultNodeProperties implements NodeProperties {

  private static final Logger logger = Logger.getLogger(DefaultNodeProperties.class);

  private final ICharmTreeViewProperties properties;
  private final MagicDisplayLabeler charmLabeler;
  private final Resources resources;
  private final CharmIdMap map;

  public DefaultNodeProperties(Resources resources, ICharmTreeViewProperties properties, CharmIdMap map) {
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
    Charm charm = findNonNullCharm(nodeId);
    String name = getNodeName(charm);
    if (charm.isTreeRoot()) {
      return name.toUpperCase();
    }
    return name;
  }

  private String textForRequirementNode(String nodeId) {
    String requirementWithCount = nodeId.replaceFirst(ICharmTreeViewProperties.REQUIREMENT + ".", "");
    String[] strings = requirementWithCount.split("\\.");
    int requirementCount = Integer.parseInt(strings[1]);
    String requirementName = resources.getString(ICharmTreeViewProperties.REQUIREMENT + "." + strings[0]);
    String charmString = resources.getString(requirementCount == 1 ? "Charms.Charm.Single" : "Charms.Charm.Multiple");
    return resources.getString("Requirement.Message", requirementCount, requirementName, charmString);
  }

  private String getNodeName(Charm charm) {
    if (charmLabeler.supportsMagic(charm)) {
      return charmLabeler.getLabelForMagic(charm);
    }
    logger.warn(format("No resource key found for node {0}. It must be a requirement or a charm.", charm.getId()));
    return resources.getString(charm.getId());
  }

  private Charm findNonNullCharm(final String charmId) {
    Charm charm = map.getCharmById(charmId);
    Preconditions.checkNotNull(charm, format("No Charm with id ''{0}'' found.", charmId));
    return charm;
  }
}