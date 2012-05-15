package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

public interface ICharmTreeViewProperties extends ISvgTreeViewProperties {

  boolean isRequirementNode(String nodeId);
}