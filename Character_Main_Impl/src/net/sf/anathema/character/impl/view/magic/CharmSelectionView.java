package net.sf.anathema.character.impl.view.magic;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.charmtree.AbstractCascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

import javax.swing.*;
import java.awt.*;

public class CharmSelectionView extends AbstractCascadeSelectionView implements ICharmSelectionView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  public CharmSelectionView(ISvgTreeViewProperties treeProperties) {
    super(treeProperties);
  }

  @Override
  public void initGui() {
    content.add(getSelectionComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    content.add(getCharmTreeView().getComponent(), GridDialogLayoutData.FILL_BOTH);
  }

  @Override
  public void addCharmSelectionListener(INodeSelectionListener listener) {
    getCharmTreeView().addNodeSelectionListener(listener);
  }

  @Override
  public void setCharmVisuals(String charmId, Color fillColor, int opacity) {
    getCharmTreeView().setNodeBackgroundColor(charmId, fillColor);
    getCharmTreeView().setNodeAlpha(charmId, opacity);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void setSpecialCharmViewVisible(ISVGSpecialNodeView charmView, boolean visible) {
    getCharmTreeView().getSpecialViewManager().setVisible(charmView, visible);
  }

  @Override
  public JComponent getCharmComponent() {
    return getCharmTreeView().getComponent();
  }
}