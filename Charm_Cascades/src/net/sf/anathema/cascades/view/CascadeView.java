package net.sf.anathema.cascades.view;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.svgtree.presenter.view.ICharmTreeViewProperties;

public class CascadeView extends AbstractCascadeSelectionView implements ICascadeView, IView {

  private JPanel content;
  private final JPanel rulesPanel = new JPanel();

  public CascadeView(ICharmTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  public JComponent getComponent() {
    if (content == null) {
      content = initGui();
    }
    return content;
  }

  private JPanel initGui() {
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    panel.add(getSelectionComponent());
    panel.add(rulesPanel, GridDialogLayoutData.RIGHT);
    JComponent treeViewComponent = getCharmTreeView().getComponent();
    treeViewComponent.setBackground(Color.WHITE);
    panel.add(treeViewComponent, GridDialogLayoutDataUtilities.createHorizontalSpanData(
        2,
        GridDialogLayoutData.FILL_BOTH));
    return panel;
  }

  public void addRuleSetComponent(JComponent component, String borderTitle) {
    rulesPanel.add(component);
    rulesPanel.setBorder(new TitledBorder(borderTitle));
  }

  public void setCharmVisuals(String id, Color color) {
    getCharmTreeView().setCharmBackgroundColor(id, color);
  }

  public void setBackgroundColor(Color color) {
    getCharmTreeView().setCanvasBackground(color);
  }

  public void unselect() {
    fillCharmComboBoxes(null, null);
  }
}