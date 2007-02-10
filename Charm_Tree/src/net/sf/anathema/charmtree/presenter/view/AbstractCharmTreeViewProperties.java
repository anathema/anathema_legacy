package net.sf.anathema.charmtree.presenter.view;

import java.awt.Cursor;
import java.awt.Dimension;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.presenter.view.ICharmTreeViewProperties;

public abstract class AbstractCharmTreeViewProperties implements ICharmTreeViewProperties {

  private Dimension dimension = new Dimension(0, 0);
  private final ICharmInfoStringBuilder tooltipTextProvider;
  private final IResources resources;

  public AbstractCharmTreeViewProperties(IResources resources) {
    this.resources = resources;
    tooltipTextProvider = new CharmInfoStringBuilder(resources);
  }

  public final String getNodeName(String nodeId) {
    if (resources.supportsKey(nodeId)) {
      return resources.getString(nodeId);
    }
    else if (isRequirementNode(nodeId)) {
      String requirementWithCount = nodeId.replaceFirst(REQUIREMENT + ".", ""); //$NON-NLS-1$ //$NON-NLS-2$
      String[] strings = requirementWithCount.split("\\."); //$NON-NLS-1$
      int requirementCount = Integer.parseInt(strings[1]);
      String requirementName = resources.getString(REQUIREMENT + "." + strings[0]); //$NON-NLS-1$
      String charmString = resources.getString(requirementCount == 1 ? "Charms.Charm.Single" : "Charms.Charm.Multiple"); //$NON-NLS-1$//$NON-NLS-2$
      return resources.getString("Requirement.Message", new Object[] { requirementCount, requirementName, charmString }); //$NON-NLS-1$
    }
    throw new UnreachableCodeReachedException("Node " + nodeId + " must be requirement or charm."); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public final boolean isRequirementNode(String nodeId) {
    return nodeId.startsWith(REQUIREMENT);
  }

  public final boolean isRootCharm(String charmId) {
    if (isRequirementNode(charmId)) {
      return false;
    }
    ICharm charm = findNonNullCharm(charmId);
    return charm.isTreeRoot();
  }

  private ICharm findNonNullCharm(String charmId) {
    ICharm charm = getCharmById(charmId);
    Ensure.ensureNotNull("Charm with id '" + charmId + " not found.", charm); //$NON-NLS-1$ //$NON-NLS-2$
    return charm;
  }

  protected abstract ICharm getCharmById(String id);

  public final Cursor getDefaultCursor() {
    return Cursor.getDefaultCursor();
  }

  public final String getToolTip(String charmId) {
    if (isRequirementNode(charmId)) {
      return ""; //$NON-NLS-1$
    }
    ICharm charm = findNonNullCharm(charmId);
    return tooltipTextProvider.getInfoString(charm);
  }

  public Dimension getDimension() {
    return dimension;
  }

  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }
}