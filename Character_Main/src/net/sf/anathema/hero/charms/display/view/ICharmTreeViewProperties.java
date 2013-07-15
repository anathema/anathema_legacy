package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.platform.tree.display.ToolTipProperties;

public interface ICharmTreeViewProperties extends ToolTipProperties {

  String REQUIREMENT = "Requirement";

  boolean isRequirementNode(String nodeId);
}