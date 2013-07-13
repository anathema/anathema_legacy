package net.sf.anathema.character.main.magic.display.view.charmtree;

import net.sf.anathema.platform.tree.display.ToolTipProperties;

public interface ICharmTreeViewProperties extends ToolTipProperties {

  String REQUIREMENT = "Requirement";

  boolean isRequirementNode(String nodeId);
}