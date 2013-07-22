package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.requirements.IndirectCharmRequirement;
import net.sf.anathema.platform.tree.display.TreeView;

public interface CharmColoring {

  void colorCharm(Charm charm);

  void setPrerequisiteVisuals(IndirectCharmRequirement requirement);

  void operateOn(TreeView treeView);
}