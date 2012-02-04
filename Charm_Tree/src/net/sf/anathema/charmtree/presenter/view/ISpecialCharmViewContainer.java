package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;

import java.awt.Color;

public interface ISpecialCharmViewContainer {
  SVGCategorizedSpecialNodeView createMultiLearnableCharmView(ISpecialCharm charm, double width, Color color);

  SVGToggleButtonSpecialNodeView createSubeffectCharmView(IMultipleEffectCharm charm, double width, Color color);

  ISVGSpecialNodeView createViewControlButton(ISVGSpecialNodeView view, double width, String label);

  void setSpecialCharmViewVisible(ISVGSpecialNodeView charmView, boolean visible);
}