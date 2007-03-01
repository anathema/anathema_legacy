package net.sf.anathema.cascades.presenter;

import java.awt.Cursor;

import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.lib.resources.IResources;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties {

  private ICharmTree charmTree;
  private IExaltedRuleSet selectedRuleset;

  public CascadeCharmTreeViewProperties(IResources resources) {
    super(resources);
  }

  public boolean isNodeSelected(String charmId) {
    return true;
  }

  public Cursor getAddCursor() {
    return Cursor.getDefaultCursor();
  }

  public Cursor getRemoveCursor() {
    return Cursor.getDefaultCursor();
  }

  public boolean isNodeSelectable(String charmId) {
    return true;
  }

  @Override
  protected ICharm getCharmById(String id) {
    ICharm charm = charmTree.getCharmById(id);
    if (charm == null) {
      charm = CharmCache.getInstance().searchCharm(id, selectedRuleset);
    }
    return charm;
  }

  public void setCharmTree(ICharmTree newCharmTree) {
    this.charmTree = newCharmTree;
  }

  public boolean isNodeDeselectable(String charmId) {
    return false;
  }

  public void setRules(IExaltedRuleSet selectedRuleset) {
    this.selectedRuleset = selectedRuleset;
  }
}