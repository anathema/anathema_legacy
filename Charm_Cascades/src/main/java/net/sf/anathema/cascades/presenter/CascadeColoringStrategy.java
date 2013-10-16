package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.coloring.CharmColoring;
import net.sf.anathema.platform.tree.display.TreeView;

public class CascadeColoringStrategy implements CharmColoring {
  private TreeView treeView;

  @Override
  public void colorCharm(Charm charm) {
    treeView.colorNode(charm.getId(), RGBColor.White);
  }

  @Override
  public void setPrerequisiteVisuals(IndirectCharmLearnPrerequisite prerequisite) {
    treeView.colorNode(prerequisite.getStringLabel(), RGBColor.White);
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }
}
