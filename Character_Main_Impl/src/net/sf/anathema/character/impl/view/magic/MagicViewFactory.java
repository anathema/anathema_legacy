package net.sf.anathema.character.impl.view.magic;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.presenter.charm.SpellViewProperties;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGViewControlButton;

public class MagicViewFactory implements IMagicViewFactory {

  public ICharmSelectionView createCharmSelectionView(final ISvgTreeViewProperties properties) {
    return new CharmSelectionView(properties);
  }

  public IComboConfigurationView createCharmComboView() {
    return new ComboConfigurationView();
  }

  public ISpellView createSpellView(final SpellViewProperties properties) {
    return new SpellView(properties);
  }

  public SVGCategorizedSpecialNodeView createMultiLearnableCharmView(
      final ISpecialCharm charm,
      final double width,
      final Color color) {
    return new SVGCategorizedSpecialNodeView(charm.getCharmId(), width, color, EssenceTemplate.SYSTEM_ESSENCE_MAX);
  }

  public SVGToggleButtonSpecialNodeView createSubeffectCharmView(
      final IMultipleEffectCharm charm,
      final double width,
      final Color color) {
    return new SVGToggleButtonSpecialNodeView(charm.getCharmId(), width, color);
  }

  public ISVGSpecialNodeView createViewControlButton(
      final ISVGSpecialNodeView view,
      final double width,
      final String label) {
    return new SVGViewControlButton(view, width, label);
  }
}