package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.platform.tree.display.TreeView;

public interface CharmColoring {

  void colorCharm(Charm charm);

  void setPrerequisiteVisuals(IndirectCharmLearnPrerequisite prerequisite);

  void operateOn(TreeView treeView);
}