package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

public interface CascadeViewFactory {

  CascadeView createCascadeView(ToolTipProperties viewProperties, NodeProperties nodeProperties);
}