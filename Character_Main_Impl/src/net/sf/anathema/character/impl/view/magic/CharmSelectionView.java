package net.sf.anathema.character.impl.view.magic;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

public class CharmSelectionView extends AbstractCascadeSelectionView implements ICharmSelectionView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  public CharmSelectionView(ISvgTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  public void initGui() {
    content.add(getSelectionComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    content.add(getCharmTreeView().getComponent(), GridDialogLayoutData.FILL_BOTH);
  }

  public void addCharmSelectionListener(INodeSelectionListener listener) {
    getCharmTreeView().addNodeSelectionListener(listener);
  }

  public void setCharmVisuals(String charmId, Color fillColor, int opacity) {
    getCharmTreeView().setNodeBackgroundColor(charmId, fillColor);
    getCharmTreeView().setNodeAlpha(charmId, opacity);
  }

  public JComponent getComponent() {
    return content;
  }

  public void setSpecialCharmViewVisible(ISVGSpecialNodeView charmView, boolean visible) {
    getCharmTreeView().getSpecialViewManager().setVisible(charmView, visible);
  }
}