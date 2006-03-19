package net.sf.anathema.character.view.magic;

import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;

public interface IMagicViewFactory {

  public ICharmSelectionView createCharmSelectionView(ICharmTreeViewProperties viewProperties);

  public IComboConfigurationView createCharmComboView();

  public ISpellView createSpellView();
}