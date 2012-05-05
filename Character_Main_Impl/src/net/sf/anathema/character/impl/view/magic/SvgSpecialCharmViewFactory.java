package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.charmtree.presenter.view.SpecialCharmViewFactory;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGViewControlButton;

import java.awt.Color;

public class SvgSpecialCharmViewFactory implements SpecialCharmViewFactory {

  @Override
  public SVGCategorizedSpecialNodeView createMultiLearnableCharmView(ISpecialCharm charm, double width, Color color) {
    return new SVGCategorizedSpecialNodeView(charm.getCharmId(), width, color, EssenceTemplate.SYSTEM_ESSENCE_MAX);
  }

  @Override
  public SVGToggleButtonSpecialNodeView createSubeffectCharmView(IMultipleEffectCharm charm, double width,
                                                                 Color color) {
    return new SVGToggleButtonSpecialNodeView(charm.getCharmId(), width, color);
  }

  @Override
  public ISVGSpecialNodeView createViewControlButton(ISVGSpecialNodeView view, double width, String label) {
    return new SVGViewControlButton(view, width, label);
  }
}
