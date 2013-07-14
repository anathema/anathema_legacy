package net.sf.anathema.cascades.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.hero.charms.display.view.AbstractCascadeSelectionView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SwingCascadeView extends AbstractCascadeSelectionView implements CascadeView, IView {
  private JPanel content = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void initGui(ToolTipProperties treeProperties, NodeProperties properties) {
    super.initGui(treeProperties, properties);
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