package net.sf.anathema.charmtree.presenter.view;

import java.awt.Cursor;
import java.awt.Dimension;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractCharmTreeViewProperties implements ICharmTreeViewProperties {

  private Dimension dimension = new Dimension(0, 0);
  private final ICharmInfoStringBuilder tooltipTextProvider;
  private final IResources resources;

  public AbstractCharmTreeViewProperties(IResources resources) {
    this.resources = resources;
    tooltipTextProvider = new CharmInfoStringBuilder(resources);
  }

  public final String getNodeName(String nodeId) {
    String name = nodeId;
    if (resources.supportsKey(nodeId)) {
      name = resources.getString(nodeId);
    }
    else if (isRequirementNode(nodeId)) {
      String requirementWithCount = nodeId.replaceFirst(REQUIREMENT + ".", ""); //$NON-NLS-1$ //$NON-NLS-2$
      String[] strings = requirementWithCount.split("\\."); //$NON-NLS-1$
      String requirementName = strings[0];
      int requirementCount = Integer.parseInt(strings[1]);
      name = requirementCount + " " + resources.getString(REQUIREMENT + "." + requirementName); //$NON-NLS-1$ //$NON-NLS-2$
      name = name.concat(" " + resources.getString( //$NON-NLS-1$
      requirementCount == 1 ? "Charms.Charm.Single" : "Charms.Charm.Multiple")); //$NON-NLS-1$//$NON-NLS-2$
    }
    return name;
  }

  public final boolean isRequirementNode(String nodeId) {
    return nodeId.startsWith(REQUIREMENT);
  }

  public final boolean isRootCharm(String charmId) {
    ICharm charm = getCharmById(charmId);
    if (charm == null) {
      return false;
    }
    return charm.isTreeRoot();
  }

  protected abstract ICharm getCharmById(String id);

  public final Cursor getDefaultCursor() {
    return Cursor.getDefaultCursor();
  }

  public final String getToolTip(String charmId) {
    ICharm charm = getCharmById(charmId);
    if (charm == null && charmId.startsWith(REQUIREMENT)) {
      return ""; //$NON-NLS-1$
    }
    if (charm == null) {
      charm = CharmCache.getInstance().searchCharm(charmId);
    }
    Ensure.ensureNotNull("Charm with id '" + charmId + " not found.", charm); //$NON-NLS-1$ //$NON-NLS-2$
    return tooltipTextProvider.getInfoString(charm);
  }

  public Dimension getDimension() {
    return dimension;
  }

  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }
}