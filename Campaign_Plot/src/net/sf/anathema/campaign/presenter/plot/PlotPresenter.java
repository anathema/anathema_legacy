package net.sf.anathema.campaign.presenter.plot;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotElementContainer;
import net.sf.anathema.campaign.model.plot.IPlotElementContainerListener;
import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.campaign.presenter.TextEditorProperties;
import net.sf.anathema.campaign.presenter.view.IPlotViewListener;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.model.IStyledTextChangeListener;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.IStyledTextManager;
import net.sf.anathema.framework.styledtext.presentation.StyledTextManager;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class PlotPresenter implements IPresenter {

  private ITextView itemNameView;
  private IStyledTextManager itemSummaryViewManager;
  private IStyledTextView itemSummaryView;
  private IPlotElementContainerListener modelListener = new IPlotElementContainerListener() {
    public void childAdded(IPlotElementContainer container, IPlotElement newChild) {
      DefaultMutableTreeNode parentNode = nodesByContainer.get(container);
      boolean childrenAllowed = newChild.getTimeUnit().hasSuccessor();
      DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(newChild, childrenAllowed);
      treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
      view.expandNode(parentNode);
      view.getComponent().repaint();
      initListening(newChild, childNode);
    }

    public void childInserted(IPlotElement insertion, IPlotElementContainer parentContainer, int index) {
      DefaultMutableTreeNode insertedNode = nodesByContainer.get(insertion);
      DefaultMutableTreeNode parentNode = nodesByContainer.get(parentContainer);
      treeModel.removeNodeFromParent(insertedNode);
      treeModel.insertNodeInto(insertedNode, parentNode, index);
      view.expandNode(parentNode);
      view.getComponent().repaint();
    }

    public void childRemoved(IPlotElement removal) {
      DefaultMutableTreeNode nodeToRemove = nodesByContainer.get(removal);
      removeFromListening(removal);
      DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) nodeToRemove.getParent();
      if (parentNode.getChildCount() == 0) {
        view.collapseNode(parentNode);
      }
      treeModel.removeNodeFromParent(nodeToRemove);
      view.getComponent().repaint();
    }

    public void childMoved(IPlotElement element, int newIndex) {
      DefaultMutableTreeNode movedChild = nodesByContainer.get(element);
      DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) movedChild.getParent();
      treeModel.removeNodeFromParent(movedChild);
      treeModel.insertNodeInto(movedChild, parentNode, newIndex);
      view.setSelectedHierarchyNode(movedChild);
      view.getComponent().repaint();
    }
  };
  private final Map<IPlotElementContainer, DefaultMutableTreeNode> nodesByContainer = new HashMap<IPlotElementContainer, DefaultMutableTreeNode>();
  private final IPlotModel plotModel;
  private final DefaultMutableTreeNode rootNode;
  private DefaultMutableTreeNode selectedNode;
  private final DefaultTreeModel treeModel;
  private final IPlotView view;
  private final IPlotViewListener viewListener = new IPlotViewListener() {
    public void contentAddedRequested(DefaultMutableTreeNode node) {
      addNewTo((IPlotElementContainer) node.getUserObject());
    }

    public void removeRequested(DefaultMutableTreeNode node) {
      plotModel.getRootElement().removeChild((IPlotElement) node.getUserObject());
    }

    public void moveToRequested(DefaultMutableTreeNode node, int index) {
      IPlotElement moveElement = (IPlotElement) node.getUserObject();
      DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
      IPlotElementContainer parentContainer = (IPlotElementContainer) parentNode.getUserObject();
      parentContainer.moveChildTo(moveElement, index);
    }

    public void moveToRequested(DefaultMutableTreeNode dropTargetNode, DefaultMutableTreeNode node, int index) {
      DefaultMutableTreeNode oldParentNode = (DefaultMutableTreeNode) node.getParent();
      if (oldParentNode == dropTargetNode.getParent()) {
        moveToRequested(node, index);
      }
      else if (oldParentNode == dropTargetNode) {
        moveToRequested(node, oldParentNode.getChildCount());
      }
      else {
        IPlotElement moveElement = (IPlotElement) node.getUserObject();
        IPlotElementContainer dropTargetContainer = (IPlotElementContainer) dropTargetNode.getUserObject();
        IPlotElementContainer oldParentContainer = (IPlotElementContainer) oldParentNode.getUserObject();
        if (dropTargetContainer.getTimeUnit() == oldParentContainer.getTimeUnit()) {
          oldParentContainer.removeChildSilent(moveElement);
          dropTargetContainer.insertChild(moveElement, dropTargetNode.getChildCount());
        }
        if (dropTargetContainer.getTimeUnit() == moveElement.getTimeUnit()) {
          oldParentContainer.removeChildSilent(moveElement);
          DefaultMutableTreeNode dropTargetParent = (DefaultMutableTreeNode) dropTargetNode.getParent();
          int targetIndex = dropTargetParent.getIndex(dropTargetNode);
          IPlotElementContainer dropTargetParentContainer = (IPlotElementContainer) dropTargetParent.getUserObject();
          dropTargetParentContainer.insertChild(moveElement, targetIndex);
        }
      }
    }

    public void selectionChangedTo(DefaultMutableTreeNode node) {
      PlotPresenter.this.selectedNode = node;
      updateButtons(node);
      itemNameView.setEnabled(node != null);
      itemSummaryView.setEnabled(node != null);
      if (node == null) {
        itemNameView.setText(null);
        itemSummaryViewManager.setText(new ITextPart[0]);
        return;
      }
      IItemDescription description = ((IPlotElement) node.getUserObject()).getDescription();
      itemNameView.setText(description.getName().getText());
      itemSummaryViewManager.setText(description.getContent().getTextParts());
    }
  };
  private final IResources resources;

  public PlotPresenter(IResources resources, IPlotView plotView, IPlotModel plotModel) {
    this.resources = resources;
    this.view = plotView;
    this.plotModel = plotModel;
    IPlotElementContainer modelRoot = plotModel.getRootElement();
    this.rootNode = new DefaultMutableTreeNode(modelRoot, true);
    initListening(modelRoot, rootNode);
    this.treeModel = new DefaultTreeModel(rootNode);
    initPlotTree();
  }

  private void initPlotTree() {
    initPlotElement(plotModel.getRootElement(), (DefaultMutableTreeNode) treeModel.getRoot());
  }

  private void initPlotElement(IPlotElement parentElement, DefaultMutableTreeNode node) {
    if (!parentElement.getTimeUnit().hasSuccessor()) {
      return;
    }
    for (IPlotElement subElement : parentElement.getChildren()) {
      DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(subElement, subElement.getTimeUnit().hasSuccessor());
      treeModel.insertNodeInto(subNode, node, node.getChildCount());
      initListening(subElement, subNode);
      initPlotElement(subElement, subNode);
    }
  }

  private void addNewTo(IPlotElementContainer container) {
    container.addChild(container.getTimeUnit().getSuccessor().getId() + " " + (container.getChildren().length + 1)); //$NON-NLS-1$
  }

  private void initDescriptionViewPresentation(IBasicItemDescriptionView descriptionView) {
    itemNameView = descriptionView.addLineTextView(resources.getString("SeriesPlot.ElementName.Label") + ":"); //$NON-NLS-1$//$NON-NLS-2$
    itemNameView.setEnabled(false);
    itemNameView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        if (selectedNode == null) {
          return;
        }
        ((IPlotElement) selectedNode.getUserObject()).getDescription().getName().setText(newValue);
        view.updateHierarchieTreeCellRenderer(new PlotTreeCellRenderer(resources));
      }
    });
    DefaultStyledDocument document = new DefaultStyledDocument();
    itemSummaryViewManager = new StyledTextManager(document);
    itemSummaryView = descriptionView.addStyledTextView(resources.getString("SeriesPlot.ElementSummary.Label") + ":", //$NON-NLS-1$ //$NON-NLS-2$
        document,
        new Dimension(200, 400),
        new TextEditorProperties(resources));
    itemSummaryView.setEnabled(false);
    itemSummaryViewManager.addStyledTextListener(new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        if (selectedNode == null) {
          return;
        }
        ((IPlotElement) selectedNode.getUserObject()).getDescription().getContent().setText(newParts);
      }
    });
    descriptionView.initGui(null);
  }

  private void initListening(IPlotElementContainer container, DefaultMutableTreeNode childNode) {
    container.addPlotElementContainerListener(modelListener);
    nodesByContainer.put(container, childNode);
  }

  public void initPresentation() {
    view.addPlotViewListener(viewListener);
    view.initSeriesHierarchyView(
        treeModel,
        new PlotTreeCellRenderer(resources),
        resources.getString("SeriesPlot.PlotTree.BorderTitle") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
    IBasicItemDescriptionView descriptionView = view.initBasicItemDescriptionView(resources.getString("SeriesPlot.ElementContent.BorderTitle") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
    initDescriptionViewPresentation(descriptionView);
    view.initGui(new PlotViewProperties(resources));
    updateButtons(null);
    new PlotPopUpMenuProvider(view.getHierarchyTreeView(), plotModel, resources).initPopupMousing();
  }

  private void removeFromListening(IPlotElementContainer container) {
    for (IPlotElement child : container.getChildren()) {
      removeFromListening(child);
    }
    container.removePlotElementContainerListener(modelListener);
    nodesByContainer.remove(container);
  }

  private void updateButtons(TreeNode node) {
    view.setAddButtonEnabled(node != null && node.getAllowsChildren());
    view.setRemoveButtonEnabled(node != null && node != rootNode);
    if (node == rootNode || node == null) {
      view.setUpButtonEnabled(false);
      view.setDownButtonEnabled(false);
      return;
    }
    TreeNode parentNode = node.getParent();
    view.setUpButtonEnabled(parentNode.getChildAt(0) != node);
    view.setDownButtonEnabled(parentNode.getChildAt(parentNode.getChildCount() - 1) != node);
  }
}