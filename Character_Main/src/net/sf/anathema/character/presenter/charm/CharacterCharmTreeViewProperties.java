package net.sf.anathema.character.presenter.charm;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.lib.resources.IResources;

public class CharacterCharmTreeViewProperties extends AbstractCharmTreeViewProperties {

  private final Cursor addCursor;
  private final Cursor removeCursor;
  private final ICharmConfiguration configuration;

  public CharacterCharmTreeViewProperties(IResources resources, ICharmConfiguration configuration) {
    super(resources);
    this.configuration = configuration;
    addCursor = Toolkit.getDefaultToolkit()
        .createCustomCursor(
            resources.getImage(this.getClass(), "CursorHandPlus.png"), new Point(0, 0), resources.getString("CharmTreeView.GUI.AddCursor")); //$NON-NLS-1$ //$NON-NLS-2$
    removeCursor = Toolkit.getDefaultToolkit()
        .createCustomCursor(
            resources.getImage(this.getClass(), "CursorHandMinus.png"), new Point(0, 0), resources.getString("CharmTreeView.GUI.RemoveCursor")); //$NON-NLS-1$ //$NON-NLS-2$

  }

  @Override
  protected ICharm getCharmById(String id) {
    if (isRequirementNode(id)) {
      return null;
    }
    return configuration.getCharmById(id);
  }

  @Override
  public Cursor getCursor(String nodeId) {
    boolean isDeselectable = isNodeDeselectable(nodeId);
    boolean isSelectable = isNodeSelectable(nodeId);
    if (isNodeSelected(nodeId)) {
      return isDeselectable ? removeCursor : getControlCursor();
    }
    return isSelectable ? addCursor : getControlCursor();
  }

  private boolean isNodeSelected(String charmId) {
    if (isRequirementNode(charmId)) {
      return false;
    }
    return configuration.isLearned(charmId);
  }

  private boolean isNodeSelectable(String charmId) {
    return !StringUtilities.isNullOrEmpty(charmId) && !isRequirementNode(charmId) && configuration.isLearnable(charmId);
  }

  private boolean isNodeDeselectable(String charmId) {
    return !StringUtilities.isNullOrEmpty(charmId)
        && !isRequirementNode(charmId)
        && configuration.isUnlearnable(charmId);
  }
}