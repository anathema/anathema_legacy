package net.sf.anathema.cascades.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;

import static net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory.createHorizontalSpanData;

public class CascadeView extends AbstractCascadeSelectionView implements ICascadeView, IView {

  private JPanel content = new JPanel(new GridDialogLayout(1, false));

  public CascadeView(ISvgTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void initGui() {
    content.add(getSelectionComponent());
    JComponent treeViewComponent = getCharmTreeView().getComponent();
    treeViewComponent.setBackground(Color.WHITE);
    content.add(treeViewComponent, createHorizontalSpanData(1, GridDialogLayoutData.FILL_BOTH));
  }

  @Override
  public void setCharmVisuals(String id, Color color) {
    getCharmTreeView().setNodeBackgroundColor(id, color);
  }

  @Override
  public void setBackgroundColor(Color color) {
    getCharmTreeView().setCanvasBackground(color);
  }

  @Override
  public void unselect() {
    super.unselect();
  }
}