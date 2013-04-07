package net.sf.anathema.cascades.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;
import net.sf.anathema.platform.tree.util.RGBColor;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class CascadeView extends AbstractCascadeSelectionView implements ICascadeView, IView {
  private JPanel content = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));

  public CascadeView(ToolTipProperties treeProperties, NodeProperties nodeProperties) {
    super(treeProperties, nodeProperties);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void initGui() {
    content.add(getSelectionComponent());
    JComponent treeViewComponent = getCharmComponent();
    treeViewComponent.setBackground(Color.WHITE);
    content.add(treeViewComponent, new CC().grow().push());
  }

  @Override
  public void setCharmVisuals(String id, RGBColor color) {
    getCharmTreeView().setNodeBackgroundColor(id, color);
  }

  @Override
  public void setBackgroundColor(RGBColor color) {
    getCharmTreeView().setCanvasBackground(color);
  }

  @Override
  public void unselect() {
    super.unselect();
  }
}