package net.sf.anathema.character.view.magic;

import net.sf.anathema.character.presenter.magic.spells.SpellViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ToolTipProperties;

public interface IMagicViewFactory {

  ICharmView createCharmSelectionView(ToolTipProperties viewProperties, NodeProperties nodeProperties);

  IComboConfigurationView createCharmComboView();

  ISpellView createSpellView(SpellViewProperties properties);
}