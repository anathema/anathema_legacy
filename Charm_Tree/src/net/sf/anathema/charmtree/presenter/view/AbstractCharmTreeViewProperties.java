package net.sf.anathema.charmtree.presenter.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

public abstract class AbstractCharmTreeViewProperties implements ICharmTreeViewProperties {

  private final ICharmInfoStringBuilder tooltipTextProvider;
  private final Cursor openHandCursor;
  private final Cursor dragHandCursor;
  private final Cursor forbiddenCursor;
  private final Cursor zoomCursor;
  private final Cursor pointCursor;

  public AbstractCharmTreeViewProperties(IResources resources, MagicDescriptionProvider magicDescriptionProvider) {
    this.tooltipTextProvider = new CharmInfoStringBuilder(resources, magicDescriptionProvider);
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
  public final boolean isRequirementNode(final String nodeId) {
    return nodeId.startsWith(REQUIREMENT);
  }

  private ICharm findNonNullCharm(final String charmId) {
    ICharm charm = getCharmById(charmId);
    Preconditions.checkNotNull(charm, "Charm with id '" + charmId + "' not found."); //$NON-NLS-1$ //$NON-NLS-2$
    return charm;
  }

  protected abstract ICharm getCharmById(String id);
  
  protected abstract ISpecialCharm getSpecialCharm(String id);

  @Override
  public final String getToolTip(String charmId) {
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
