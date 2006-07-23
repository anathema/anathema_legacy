package net.sf.anathema.character.impl.view.repository;

import java.awt.Component;
import java.util.Enumeration;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.resources.CharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.view.repository.ICharacterTemplateTree;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.resources.IResources;

public class CharacterTemplateTree implements ICharacterTemplateTree {

  private JTree templateTree;
  private GenericControl<TreeSelectionListener> control = new GenericControl<TreeSelectionListener>();
  private final ICharacterGenerics generics;
  private final IResources resources;

  public CharacterTemplateTree(ICharacterGenerics generics, IResources resources) {
    this.generics = generics;
    this.resources = resources;
  }

  public void initTemplateTree() {
    DefaultTreeModel treeModel = createTreeModel();
    this.templateTree = new JTree(treeModel);
    templateTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    templateTree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent event) {
        fireValueChangedEvent(event);
      }
    });
    templateTree.setCellRenderer(createRenderer());
    DefaultMutableTreeNode root = (DefaultMutableTreeNode) templateTree.getModel().getRoot();
    for (Enumeration<DefaultMutableTreeNode> typeEnumeration = root.children(); typeEnumeration.hasMoreElements();) {
      TreeNode[] path = typeEnumeration.nextElement().getPath();
      templateTree.expandPath(new TreePath(path));
    }
    templateTree.setRootVisible(false);
  }

  private TreeCellRenderer createRenderer() {
    return new DefaultTreeCellRenderer() {
      @Override
      public Component getTreeCellRendererComponent(
          JTree tree,
          Object value,
          boolean sel,
          boolean expanded,
          boolean leaf,
          int row,
          boolean focus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object object = node.getUserObject();
        if (object instanceof CharacterType) {
          CharacterType characterType = (CharacterType) object;
          String characterTypeString = resources.getString("CharacterGenerator.NewCharacter." //$NON-NLS-1$
              + (characterType).getId()
              + ".Name"); //$NON-NLS-1$
          Component renderComponent = super.getTreeCellRendererComponent(
              tree,
              characterTypeString,
              sel,
              expanded,
              leaf,
              row,
              focus);
          setIcon(new CharacterTemplateResourceProvider(resources).getTinyTypeIcon(characterType));
          return renderComponent;
        }
        if (object instanceof ITemplateTypeAggregation) {
          ITemplateTypeAggregation template = (ITemplateTypeAggregation) object;
          String templateString = resources.getString(template.getPresentationProperties().getNewActionResource());
          Component renderComponent = super.getTreeCellRendererComponent(
              tree,
              templateString,
              sel,
              expanded,
              leaf,
              row,
              focus);
          setIcon(new CharacterTemplateResourceProvider(resources).getTinyBallResource(template.getTemplateType().getCharacterType()));
          return renderComponent;
        }
        return super.getTreeCellRendererComponent(templateTree, value, sel, expanded, leaf, row, focus);
      }
    };
  }

  private DefaultTreeModel createTreeModel() {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(
        resources.getString("CharacterDialog.TemplateTree.RootNode"), //$NON-NLS-1$
        true);
    DefaultTreeModel treeModel = new DefaultTreeModel(root);
    TemplateTypeAggregator aggregator = new TemplateTypeAggregator(generics.getTemplateRegistry());
    for (CharacterType type : CharacterType.values()) {
      ITemplateTypeAggregation[] aggregations = aggregator.aggregateTemplates(type);
      if (aggregations.length == 0) {
        continue;
      }
      DefaultMutableTreeNode node = new DefaultMutableTreeNode(type);
      treeModel.insertNodeInto(node, root, root.getChildCount());
      for (ITemplateTypeAggregation aggregation : aggregations) {
        DefaultMutableTreeNode aggregationNode = new DefaultMutableTreeNode(aggregation);
        treeModel.insertNodeInto(aggregationNode, node, node.getChildCount());
      }
    }
    return treeModel;
  }

  private void fireValueChangedEvent(final TreeSelectionEvent event) {
    control.forAllDo(new IClosure<TreeSelectionListener>() {
      public void execute(TreeSelectionListener input) {
        input.valueChanged(event);
      }
    });
  }

  public JComponent getComponent() {
    return templateTree;
  }

  public boolean isTemplateTypeSelected() {
    return getSelectedTemplate() != null;
  }

  public void addTreeSelectionListener(TreeSelectionListener listener) {
    control.addListener(listener);
  }

  public ITemplateTypeAggregation getSelectedTemplate() {
    TreePath selectionPath = templateTree.getSelectionModel().getSelectionPath();
    if (selectionPath == null) {
      return null;
    }
    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
    Object userObject = selectedNode.getUserObject();
    if (userObject instanceof ITemplateTypeAggregation) {
      return (ITemplateTypeAggregation) userObject;
    }
    return null;
  }
}