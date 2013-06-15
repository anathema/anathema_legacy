package net.sf.anathema.character.view.magic;

import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;

public interface IMagicViewFactory {

  ICharmView createCharmSelectionView(ToolTipProperties viewProperties, NodeProperties nodeProperties);
}