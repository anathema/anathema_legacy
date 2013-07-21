package net.sf.anathema.cascades.module;

import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

public interface CascadeViewFactory {

  CharmView createCascadeView(ToolTipProperties viewProperties, NodeProperties nodeProperties);
}