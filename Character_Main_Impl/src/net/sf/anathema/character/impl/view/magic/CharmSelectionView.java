package net.sf.anathema.character.impl.view.magic;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.charmtree.batik.intvalue.SVGSpecialCharmViewManager;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionListener;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.ISVGMultiLearnableCharmView;
import net.sf.anathema.charmtree.presenter.view.ISpecialCharmViewManager;
import net.sf.anathema.lib.util.IIdentificate;

public class CharmSelectionView extends AbstractCascadeSelectionView implements ICharmSelectionView {

  private JPanel content;
  private final ISpecialCharmViewManager<ISVGMultiLearnableCharmView> svgManager = new SVGSpecialCharmViewManager(
      getCharmTreeView());

  public CharmSelectionView(ICharmTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  public void initGui() {
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    panel.add(getSelectionComponent());
    JComponent treeViewComponent = getCharmTreeView().getComponent();
    panel.add(treeViewComponent, GridDialogLayoutData.FILL_BOTH);
    this.content = panel;
  }

  public void addCharmSelectionListener(ICharmSelectionListener listener) {
    getCharmTreeView().addCharmSelectionListener(listener);
  }

  public void setCharmVisuals(String charmId, Color fillColor, int opacity) {
    getCharmTreeView().setCharmBackgroundColor(charmId, fillColor);
    getCharmTreeView().setCharmAlpha(charmId, opacity);
  }

  public JComponent getComponent() {
    return content;
  }

  public void setSpecialCharmViewVisible(ISVGMultiLearnableCharmView charmView, boolean visible) {
    svgManager.setSpecialCharmViewVisible(getCharmTreeView(), charmView, visible);
  }

  public void fillCharmTypeBox(IIdentificate[] charmGroups) {
    getTypeComboBox().setObjects(charmGroups);
  }

  public boolean needsScrollbar() {
    return false;
  }
}