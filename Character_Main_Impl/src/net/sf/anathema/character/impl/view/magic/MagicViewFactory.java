package net.sf.anathema.character.impl.view.magic;

import java.awt.Color;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.charmtree.batik.intvalue.SVGMultiLearnableCharmView;
import net.sf.anathema.charmtree.batik.intvalue.SVGSubeffectCharmView;
import net.sf.anathema.charmtree.batik.intvalue.SVGViewControlButton;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.ISVGSpecialCharmView;

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

  public SVGMultiLearnableCharmView createMultiLearnableCharmView(ISpecialCharm charm, double width, Color color) {
    return new SVGMultiLearnableCharmView(charm.getCharmId(), width, color);
  }

  public SVGSubeffectCharmView createSubeffectCharmView(ISubeffectCharm charm, double width, Color color) {
    return new SVGSubeffectCharmView(charm.getCharmId(), width, color);
  }

  public ISVGSpecialCharmView createViewControlButton(ISVGSpecialCharmView view, double width, String label) {
    return new SVGViewControlButton(view, width, label);
  }
}