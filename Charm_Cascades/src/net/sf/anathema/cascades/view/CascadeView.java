package net.sf.anathema.cascades.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CascadeView extends AbstractCascadeSelectionView implements ICascadeView, IView {

  private JPanel content = new JPanel(new GridDialogLayout(2, false));
  private final JPanel rulesPanel = new JPanel();

  public CascadeView(ISvgTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  public void initGui() {
    content.add(getSelectionComponent());
    content.add(rulesPanel, GridDialogLayoutData.RIGHT);
    JComponent treeViewComponent = getCharmTreeView().getComponent();
    treeViewComponent.setBackground(Color.WHITE);
    content.add(treeViewComponent, GridDialogLayoutDataFactory.createHorizontalSpanData(2, GridDialogLayoutData.FILL_BOTH));
  }

  @Override
  public void addRuleSetComponent(JComponent component, String borderTitle) {
    rulesPanel.add(component);
    rulesPanel.setBorder(new TitledBorder(borderTitle));
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