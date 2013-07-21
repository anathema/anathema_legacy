package net.sf.anathema.cascades.module;

import net.sf.anathema.hero.charms.display.view.CascadeSelectionView;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

public interface CascadeViewFactory {

  CascadeSelectionView createCascadeView(ToolTipProperties viewProperties, NodeProperties nodeProperties);
}