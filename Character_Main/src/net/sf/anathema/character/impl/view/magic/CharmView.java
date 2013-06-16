package net.sf.anathema.character.impl.view.magic;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.charmtree.view.ICharmView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.presenter.view.ISpecialNodeView;
import net.sf.anathema.platform.tree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;
import net.sf.anathema.framework.ui.RGBColor;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CharmView extends AbstractCascadeSelectionView implements ICharmView {
  private final JPanel content = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));

  public CharmView(IntegerViewFactory integerDisplayFactory) {
    getCharmTreeView().registerSpecialType(IIntValueView.class, new SpecialIntDisplayFactory(integerDisplayFactory));
    getCharmTreeView().registerSpecialType(IBooleanValueView.class, new SpecialBooleanDisplayFactory());
  }

  @Override
  public void initGui(ToolTipProperties treeProperties, NodeProperties properties) {
    super.initGui(treeProperties, properties);
    content.add(getSelectionComponent(), new CC().growX());
    content.add(getCharmComponent(), new CC().grow().push());
  }

  @Override
  public void addCharmInteractionListener(NodeInteractionListener listener) {
    getCharmTreeView().addNodeInteractionListener(listener);
  }

  @Override
  public void setCharmVisuals(String charmId, RGBColor fillColor, int opacity) {
    getCharmTreeView().setNodeBackgroundColor(charmId, fillColor);
    getCharmTreeView().setNodeAlpha(charmId, opacity);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void setSpecialCharmViewVisible(ISpecialNodeView charmView, boolean visible) {
    if (visible) {
      getCharmTreeView().addSpecialControl(charmView.getNodeId(), charmView);
    }
  }
}