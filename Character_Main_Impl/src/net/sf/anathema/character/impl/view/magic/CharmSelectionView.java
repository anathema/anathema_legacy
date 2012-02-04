package net.sf.anathema.character.impl.view.magic;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGViewControlButton;

import javax.swing.*;
import java.awt.*;

public class CharmSelectionView extends AbstractCascadeSelectionView implements ICharmSelectionView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  public CharmSelectionView(ISvgTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  @Override
  public void initGui() {
    content.add(getSelectionComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    content.add(getCharmTreeView().getComponent(), GridDialogLayoutData.FILL_BOTH);
  }

  @Override
  public void addCharmSelectionListener(INodeSelectionListener listener) {
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
  public JComponent getCharmComponent() {
    return getCharmTreeView().getComponent();
  }

  @Override
  public SVGCategorizedSpecialNodeView createMultiLearnableCharmView(
          final ISpecialCharm charm,
          final double width,
          final Color color) {
    return new SVGCategorizedSpecialNodeView(charm.getCharmId(), width, color, EssenceTemplate.SYSTEM_ESSENCE_MAX);
  }

  @Override
  public SVGToggleButtonSpecialNodeView createSubeffectCharmView(
          final IMultipleEffectCharm charm,
          final double width,
          final Color color) {
    return new SVGToggleButtonSpecialNodeView(charm.getCharmId(), width, color);
  }

  @Override
  public ISVGSpecialNodeView createViewControlButton(
          final ISVGSpecialNodeView view,
          final double width,
          final String label) {
    return new SVGViewControlButton(view, width, label);
  }
}