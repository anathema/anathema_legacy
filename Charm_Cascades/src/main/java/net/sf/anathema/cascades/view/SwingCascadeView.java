package net.sf.anathema.cascades.view;

import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.view.AbstractCascadeSelectionView;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

public class SwingCascadeView extends AbstractCascadeSelectionView implements CascadeView {

  @Override
  public void initGui(ToolTipProperties treeProperties, NodeProperties properties) {
    super.initGui(treeProperties, properties);
    getCharmTreeView().setCanvasBackground(RGBColor.White);
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