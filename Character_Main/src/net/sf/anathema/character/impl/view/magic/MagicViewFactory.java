package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;

public class MagicViewFactory implements IMagicViewFactory {

  private final IntegerViewFactory integerDisplayFactory;

  public MagicViewFactory(IntegerViewFactory integerDisplayFactory) {
    this.integerDisplayFactory = integerDisplayFactory;
  }

  @Override
  public ICharmView createCharmSelectionView(ToolTipProperties properties, NodeProperties nodeProperties) {
    return new CharmView(properties, nodeProperties, integerDisplayFactory);
  }
}