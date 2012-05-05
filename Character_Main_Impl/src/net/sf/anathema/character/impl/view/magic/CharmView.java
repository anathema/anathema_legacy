package net.sf.anathema.character.impl.view.magic;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.platform.svgtree.presenter.view.CharmInteractionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGViewControlButton;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;

public class CharmView extends AbstractCascadeSelectionView implements ICharmView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  public CharmView(ISvgTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  @Override
  public void initGui() {
    content.add(getSelectionComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    content.add(getCharmComponent(), GridDialogLayoutData.FILL_BOTH);
  }

  @Override
  public void addCharmInteractionListener(CharmInteractionListener listener) {
    getCharmTreeView().addNodeSelectionListener(listener);
  }

  @Override
  public void setCharmVisuals(String charmId, Color fillColor, int opacity) {
    getCharmTreeView().setNodeBackgroundColor(charmId, fillColor);
    getCharmTreeView().setNodeAlpha(charmId, opacity);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void setSpecialCharmViewVisible(ISVGSpecialNodeView charmView, boolean visible) {
    getCharmTreeView().getSpecialViewManager().setVisible(charmView, visible);
  }

  @Override
  public SVGCategorizedSpecialNodeView createMultiLearnableCharmView(ISpecialCharm charm, double width, Color color) {
    return new SVGCategorizedSpecialNodeView(charm.getCharmId(), width, color, EssenceTemplate.SYSTEM_ESSENCE_MAX);
  }

  @Override
  public SVGToggleButtonSpecialNodeView createSubeffectCharmView(IMultipleEffectCharm charm, double width, Color color) {
    return new SVGToggleButtonSpecialNodeView(charm.getCharmId(), width, color);
  }

  @Override
  public ISVGSpecialNodeView createViewControlButton(ISVGSpecialNodeView view, double width, String label) {
    return new SVGViewControlButton(view, width, label);
  }
}
