package net.sf.anathema.character.view.magic;

import java.awt.Color;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.charmtree.batik.intvalue.SVGMultiLearnableCharmView;
import net.sf.anathema.charmtree.batik.intvalue.SVGSubeffectCharmView;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.ISVGSpecialCharmView;

public interface IMagicViewFactory {

  public ICharmSelectionView createCharmSelectionView(ICharmTreeViewProperties viewProperties);

  public IComboConfigurationView createCharmComboView();

  public ISpellView createSpellView();

  public SVGMultiLearnableCharmView createMultiLearnableCharmView(ISpecialCharm charm, double width, Color color);

  public SVGSubeffectCharmView createSubeffectCharmView(ISubeffectCharm visited, double charmWidth, Color characterColor);

  public ISVGSpecialCharmView createViewControlButton(ISVGSpecialCharmView view, double width, String label);
}