package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;

public interface AlienCharmPresenter {
  void initPresentation(ObjectSelectionView<Identifier> typeSelector);
}
