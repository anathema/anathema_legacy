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
import net.sf.anathema.charmtree.presenter.view.ISVGSpecialCharmView;
import net.sf.anathema.charmtree.presenter.view.ISpecialCharmViewManager;

public class CharmSelectionView extends AbstractCascadeSelectionView implements ICharmSelectionView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));
  private final ISpecialCharmViewManager<ISVGSpecialCharmView> svgManager = new SVGSpecialCharmViewManager(
      getCharmTreeView());

  public CharmSelectionView(ICharmTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  public void initGui() {
    content.add(getSelectionComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    content.add(getCharmTreeView().getComponent(), GridDialogLayoutData.FILL_BOTH);
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

  public void setSpecialCharmViewVisible(ISVGSpecialCharmView charmView, boolean visible) {
    svgManager.setSpecialCharmViewVisible(getCharmTreeView(), charmView, visible);
  }
}