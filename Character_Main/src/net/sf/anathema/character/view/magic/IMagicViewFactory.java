package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.presenter.magic.spells.SpellViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.charmtree.presenter.view.SpecialCharmViewFactory;
import net.sf.anathema.platform.svgtree.presenter.view.TreeViewProperties;

public interface IMagicViewFactory {

  ICharmView createCharmSelectionView(TreeViewProperties viewProperties);

  IComboConfigurationView createCharmComboView();

  ISpellView createSpellView(SpellViewProperties properties);

  SpecialCharmViewFactory createSpecialViewFactory();
}
