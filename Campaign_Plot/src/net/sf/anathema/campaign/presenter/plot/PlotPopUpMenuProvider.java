package net.sf.anathema.campaign.presenter.plot;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.campaign.presenter.view.plot.ITreeView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.IResources;

public class PlotPopUpMenuProvider {

  private final ITreeView treeView;
  private final MouseListener popUpMouser = new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
      checkPopup(e);
    }
  };
  private final IPlotModel plotModel;
  private final IResources resources;
  private final BasicUi basicUi;

  public PlotPopUpMenuProvider(ITreeView treeView, IPlotModel plotModel, IResources resources) {
    this.treeView = treeView;
    this.plotModel = plotModel;
    this.resources = resources;
    this.basicUi = new BasicUi(resources);
  }

  public void initPopupMousing() {
    this.treeView.getTreeComponent().addMouseListener(popUpMouser);
  }

  private void checkPopup(MouseEvent e) {
    if (e.isMetaDown() && e.getSource() instanceof Component) {
      TreePath clickedPath = treeView.getPathForLocation(e.getX(), e.getY());
      if (clickedPath == null) {
        return;
      }
      TreePath[] selectedPaths = treeView.getSelectionPaths();
      if (selectedPaths == null || selectedPaths.length == 0 || !contains(selectedPaths, clickedPath)) {
        treeView.setSelectionPath(clickedPath);
      }
      DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) clickedPath.getLastPathComponent();
      showPopupMenu((IPlotElement) selectedNode.getUserObject(), e.getX(), e.getY());
    }
  }

  private void showPopupMenu(final IPlotElement element, int x, int y) {
    final IPlotElement parentElement = plotModel.getParentElement(element);
    JPopupMenu menu = new JPopupMenu();
    SmartAction addAction = new SmartAction(resources.getString("SeriesPlot.PopUp.AddAction"), basicUi.getAddIcon()) { //$NON-NLS-1$
      @Override 
      protected void execute(Component parentComponent) {
        String childUnitId = element.getTimeUnit().getSuccessor().getId();
        element.addChild(childUnitId + " " + (element.getChildren().length + 1)); //$NON-NLS-1$
      }
    };
    addAction.setEnabled(element.getTimeUnit().hasSuccessor());
    menu.add(addAction);
    SmartAction removeAction = new SmartAction(resources.getString("SeriesPlot.PopUp.RemoveAction"), basicUi.getRemoveIcon()) { //$NON-NLS-1$
      @Override protected void execute(Component parentComponent) {
        if (parentElement != null) {
          parentElement.removeChild(element);
        }
      }
    };
    menu.add(removeAction);
    removeAction.setEnabled(parentElement != null);
    menu.show(treeView.getTreeComponent(), x, y);
  }

  private boolean contains(TreePath[] paths, TreePath path) {
    for (int i = 0; i < paths.length; i++) {
      if (path.equals(paths[i])) {
        return true;
      }
    }
    return false;
  }

}
