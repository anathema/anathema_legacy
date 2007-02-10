package net.sf.anathema.character.view.magic;

import java.awt.Color;

import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.presenter.charm.SpellViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.platform.svgtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialCharmView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGMultiLearnableCharmView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGSubeffectCharmView;

public interface IMagicViewFactory {

  public ICharmSelectionView createCharmSelectionView(ICharmTreeViewProperties viewProperties);

  public IComboConfigurationView createCharmComboView();

  public ISpellView createSpellView(SpellViewProperties properties);

  public SVGMultiLearnableCharmView createMultiLearnableCharmView(ISpecialCharm charm, double width, Color color);

  public SVGSubeffectCharmView createSubeffectCharmView(
      IMultipleEffectCharm visited,
      double charmWidth,
      Color characterColor);

  public ISVGSpecialCharmView createViewControlButton(ISVGSpecialCharmView view, double width, String label);
}