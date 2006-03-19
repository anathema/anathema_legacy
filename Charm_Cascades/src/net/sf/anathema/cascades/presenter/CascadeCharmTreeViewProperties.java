package net.sf.anathema.cascades.presenter;

import java.awt.Cursor;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.lib.resources.IResources;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties {

  private ICharmTree charmTree;

  public CascadeCharmTreeViewProperties(IResources resources) {
    super(resources);
  }

  public boolean isCharmSelected(String charmId) {
    return true;
  }

  public Cursor getAddCursor() {
    return Cursor.getDefaultCursor();
  }

  public Cursor getRemoveCursor() {
    return Cursor.getDefaultCursor();
  }

  public boolean isCharmLearnable(String charmId) {
    return true;
  }

  @Override
  protected ICharm getCharmById(String id) {
    return charmTree.getCharmByID(id);
  }

  public void setCharmTree(ICharmTree newCharmTree) {
    this.charmTree = newCharmTree;
  }

  public boolean isCharmUnlearnable(String charmId) {
    return false;
  }
}