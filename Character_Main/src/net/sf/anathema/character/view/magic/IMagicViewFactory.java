package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.presenter.charm.SpellViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

public interface IMagicViewFactory {

  ICharmSelectionView createCharmSelectionView(ISvgTreeViewProperties viewProperties);

  IComboConfigurationView createCharmComboView();

  ISpellView createSpellView(SpellViewProperties properties);
}