package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;

public class MagicViewFactory implements IMagicViewFactory {

  public ICharmSelectionView createCharmSelectionView(ICharmTreeViewProperties properties) {
    return new CharmSelectionView(properties);
  }

  public IComboConfigurationView createCharmComboView() {
    return new ComboConfigurationView();
  }

  public ISpellView createSpellView() {
    return new SpellView();
  }
}