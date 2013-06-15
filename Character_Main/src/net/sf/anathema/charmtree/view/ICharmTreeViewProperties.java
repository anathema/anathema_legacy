package net.sf.anathema.charmtree.view;

import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;

public interface ICharmTreeViewProperties extends ToolTipProperties {

  String REQUIREMENT = "Requirement";

  boolean isRequirementNode(String nodeId);
}