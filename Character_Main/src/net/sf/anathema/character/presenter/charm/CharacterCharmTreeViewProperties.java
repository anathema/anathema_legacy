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
            resources.getImage(this.getClass(), "CursorPlus.png"), new Point(0, 0), resources.getString("CharmTreeView.GUI.AddCursor")); //$NON-NLS-1$ //$NON-NLS-2$
    removeCursor = Toolkit.getDefaultToolkit()
        .createCustomCursor(
            resources.getImage(this.getClass(), "CursorMinus.png"), new Point(0, 0), resources.getString("CharmTreeView.GUI.RemoveCursor")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public boolean isNodeSelected(String charmId) {
    if (isRequirementNode(charmId)) {
      return false;
    }
    return configuration.isLearned(charmId);
  }

  public Cursor getAddCursor() {
    return addCursor;
  }

  public Cursor getRemoveCursor() {
    return removeCursor;
  }

  public boolean isNodeSelectable(String charmId) {
    return !StringUtilities.isNullOrEmpty(charmId) && !isRequirementNode(charmId) && configuration.isLearnable(charmId);
  }

  public boolean isNodeDeselectable(String charmId) {
    return !StringUtilities.isNullOrEmpty(charmId)
        && !isRequirementNode(charmId)
        && configuration.isUnlearnable(charmId);
  }

  @Override
  protected ICharm getCharmById(String id) {
    if (isRequirementNode(id)) {
      return null;
    }
    return configuration.getCharmById(id);
  }
}