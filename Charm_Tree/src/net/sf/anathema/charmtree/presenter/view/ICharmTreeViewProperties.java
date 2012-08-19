package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.platform.svgtree.presenter.view.ToolTipProperties;

public interface ICharmTreeViewProperties extends ToolTipProperties {

  String REQUIREMENT = "Requirement"; //$NON-NLS-1$

  boolean isRequirementNode(String nodeId);
}