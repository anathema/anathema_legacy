package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;

public interface CascadeViewFactory {

  CascadeView createCascadeView(ToolTipProperties viewProperties, NodeProperties nodeProperties);
}