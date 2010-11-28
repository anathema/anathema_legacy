package net.sf.anathema.character.view.magic;

import java.awt.Color;

import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.presenter.charm.SpellViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;

public interface IMagicViewFactory {

  public ICharmSelectionView createCharmSelectionView(ISvgTreeViewProperties viewProperties);

  public IComboConfigurationView createCharmComboView();

  public ISpellView createSpellView(SpellViewProperties properties);

  public SVGCategorizedSpecialNodeView createMultiLearnableCharmView(ISpecialCharm charm, double width, Color color);

  public SVGToggleButtonSpecialNodeView createSubeffectCharmView(
      IMultipleEffectCharm visited,
      double charmWidth,
      Color characterColor);

  public ISVGSpecialNodeView createViewControlButton(ISVGSpecialNodeView view, double width, String label);
}