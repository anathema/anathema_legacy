package net.sf.anathema.hero.charms.display;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.hero.charms.display.view.AbstractCascadeSelectionView;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.hero.charms.display.special.SpecialBooleanDisplayFactory;
import net.sf.anathema.hero.charms.display.special.SpecialIntDisplayFactory;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.tree.display.ISpecialNodeView;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class SwingCharmView extends AbstractCascadeSelectionView implements CharmView, IView {
  private final JPanel content = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));

  public SwingCharmView(IntegerViewFactory integerDisplayFactory) {
    getCharmTreeView().registerSpecialType(IntValueView.class, new SpecialIntDisplayFactory(integerDisplayFactory));
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