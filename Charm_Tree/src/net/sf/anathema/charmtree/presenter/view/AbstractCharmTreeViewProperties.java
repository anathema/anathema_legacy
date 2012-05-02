package net.sf.anathema.charmtree.presenter.view;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.magic.MagicDisplayLabeler;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

public abstract class AbstractCharmTreeViewProperties implements ICharmTreeViewProperties {

  private static final String REQUIREMENT = "Requirement"; //$NON-NLS-1$
  private final ICharmInfoStringBuilder tooltipTextProvider;
  private final MagicDisplayLabeler charmLabeler;
  private final IResources resources;
  private final Cursor openHandCursor;
  private final Cursor dragHandCursor;
  private final Cursor forbiddenCursor;
  private final Cursor zoomCursor;
  private final Cursor pointCursor;

  public AbstractCharmTreeViewProperties(IResources resources, MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
    this.tooltipTextProvider = new CharmInfoStringBuilder(resources, magicDescriptionProvider);
    this.charmLabeler = new MagicDisplayLabeler(resources);
    this.openHandCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        resources.getImage(this.getClass(), "CursorHandOpen.png"), //$NON-NLS-1$
        new Point(5, 0),
        resources.getString("CharmTreeView.GUI.HandCursor")); //$NON-NLS-1$
    this.dragHandCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        resources.getImage(this.getClass(), "CursorHandGrab.png"), //$NON-NLS-1$
        new Point(5, 0),
        resources.getString("CharmTreeView.GUI.GrabCursor")); //$NON-NLS-1$
    this.forbiddenCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        resources.getImage(this.getClass(), "CursorDisallow.png"), //$NON-NLS-1$
        new Point(5, 0),
        resources.getString("CharmTreeView.GUI.ForbiddenCursor")); //$NON-NLS-1$
    this.zoomCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        resources.getImage(this.getClass(), "CursorZoom.png"), //$NON-NLS-1$
        new Point(5, 0),
        resources.getString("CharmTreeView.GUI.ZoomCursor")); //$NON-NLS-1$
    this.pointCursor = Toolkit.getDefaultToolkit()
        .createCustomCursor(
            resources.getImage(this.getClass(), "CursorHandPoint.png"), new Point(5, 0), resources.getString("CharmTreeView.GUI.PointCursor")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public Cursor getDefaultCursor() {
    return openHandCursor;
  }

  @Override
  public Cursor getDragCursor() {
    return dragHandCursor;
  }

  @Override
  public Cursor getForbiddenCursor() {
    return forbiddenCursor;
  }

  @Override
  public Cursor getZoomCursor() {
    return zoomCursor;
  }

  @Override
  public final String getNodeName(final String nodeId) {
	ICharm charm = getCharmById(nodeId);
    if (charmLabeler.supportsMagic(charm)) {
      return charmLabeler.getLabelForMagic(charm);
    }
    else if (isRequirementNode(nodeId)) {
      String requirementWithCount = nodeId.replaceFirst(REQUIREMENT + ".", ""); //$NON-NLS-1$ //$NON-NLS-2$
      String[] strings = requirementWithCount.split("\\."); //$NON-NLS-1$
	  int requirementCount = Integer.parseInt(strings[1]);
	  String requirementName = resources.getString(REQUIREMENT + "." + strings[0]); //$NON-NLS-1$
	  String charmString = resources.getString(requirementCount == 1 ? "Charms.Charm.Single" : "Charms.Charm.Multiple"); //$NON-NLS-1$//$NON-NLS-2$
	  return resources.getString("Requirement.Message", new Object[] { requirementCount, requirementName, charmString }); //$NON-NLS-1$
    }
    Logger.getLogger(getClass()).warn(
        "No resource key found for node " + nodeId + ". It must be a requirement or a charm."); //$NON-NLS-1$ //$NON-NLS-2$
    return resources.getString(nodeId);
  }

  @Override
  public final boolean isRequirementNode(final String nodeId) {
    return nodeId.startsWith(REQUIREMENT);
  }

  @Override
  public final boolean isRootNode(final String charmId) {
    if (isRequirementNode(charmId)) {
      return false;
    }
    ICharm charm = findNonNullCharm(charmId);
    return charm.isTreeRoot();
  }

  private ICharm findNonNullCharm(final String charmId) {
    ICharm charm = getCharmById(charmId);
    Ensure.ensureNotNull("Charm with id '" + charmId + "' not found.", charm); //$NON-NLS-1$ //$NON-NLS-2$
    return charm;
  }

  protected abstract ICharm getCharmById(String id);
  
  protected abstract ISpecialCharm getSpecialCharm(String id);

  @Override
  public final String getToolTip(final String charmId) {
    if (isRequirementNode(charmId)) {
      return null;
    }
    ICharm charm = findNonNullCharm(charmId);
    ISpecialCharm specialCharm = getSpecialCharm(charmId);
    return tooltipTextProvider.getInfoString(charm, specialCharm);
  }

  @Override
  public Cursor getControlCursor() {
    return pointCursor;
  }
}
