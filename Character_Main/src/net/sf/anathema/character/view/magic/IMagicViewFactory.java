package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.presenter.magic.spells.SpellViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

public interface IMagicViewFactory {

  ICharmView createCharmSelectionView(ISvgTreeViewProperties viewProperties);

  IComboConfigurationView createCharmComboView();

  ISpellView createSpellView(SpellViewProperties properties);
}
