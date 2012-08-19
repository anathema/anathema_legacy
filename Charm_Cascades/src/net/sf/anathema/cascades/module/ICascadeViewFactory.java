package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ToolTipProperties;

public interface ICascadeViewFactory {

  ICascadeView createCascadeView(ToolTipProperties viewProperties, NodeProperties nodeProperties);
}