package net.sf.anathema.character.impl.view.magic;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.presenter.charm.SpellViewProperties;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.charmtree.batik.intvalue.SVGMultiLearnableCharmView;
import net.sf.anathema.charmtree.batik.intvalue.SVGSubeffectCharmView;
import net.sf.anathema.charmtree.batik.intvalue.SVGViewControlButton;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.platform.svgtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialCharmView;

public class MagicViewFactory implements IMagicViewFactory {

  public ICharmSelectionView createCharmSelectionView(final ICharmTreeViewProperties properties) {
    return new CharmSelectionView(properties);
  }

  public IComboConfigurationView createCharmComboView() {
    return new ComboConfigurationView();
  }

  public ISpellView createSpellView(final SpellViewProperties properties) {
    return new SpellView(properties);
  }

  public SVGMultiLearnableCharmView createMultiLearnableCharmView(
      final ISpecialCharm charm,
      final double width,
      final Color color) {
    return new SVGMultiLearnableCharmView(charm.getCharmId(), width, color, EssenceTemplate.SYSTEM_ESSENCE_MAX);
  }

  public SVGSubeffectCharmView createSubeffectCharmView(
      final IMultipleEffectCharm charm,
      final double width,
      final Color color) {
    return new SVGSubeffectCharmView(charm.getCharmId(), width, color);
  }

  public ISVGSpecialCharmView createViewControlButton(
      final ISVGSpecialCharmView view,
      final double width,
      final String label) {
    return new SVGViewControlButton(view, width, label);
  }
}