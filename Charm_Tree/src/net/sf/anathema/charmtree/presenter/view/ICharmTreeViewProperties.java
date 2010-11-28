package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

public interface ICharmTreeViewProperties extends ISvgTreeViewProperties {

  public boolean isRequirementNode(String nodeId);
}