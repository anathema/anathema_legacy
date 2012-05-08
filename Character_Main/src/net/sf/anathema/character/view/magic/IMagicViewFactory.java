package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.presenter.magic.spells.SpellViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.charmtree.presenter.view.SpecialCharmViewFactory;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.svgtree.presenter.view.TreeViewProperties;

public interface IMagicViewFactory {

  ICharmView createCharmSelectionView(TreeViewProperties viewProperties, NodeProperties nodeProperties);

  IComboConfigurationView createCharmComboView();

  ISpellView createSpellView(SpellViewProperties properties);

  SpecialCharmViewFactory createSpecialViewFactory();
}
