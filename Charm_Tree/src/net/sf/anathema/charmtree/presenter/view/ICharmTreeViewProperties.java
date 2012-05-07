package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.platform.svgtree.presenter.view.TreeViewProperties;

public interface ICharmTreeViewProperties extends TreeViewProperties {

  boolean isRequirementNode(String nodeId);
}