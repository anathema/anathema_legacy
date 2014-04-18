package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.initialization.ForItemType;
import net.sf.anathema.initialization.ItemTypeCollection;
import net.sf.anathema.lib.util.Closure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepositoryTreePresenter {

  private final Environment environment;
  private final IApplicationModel model;
  private final ItemTypeCollection itemTypeCollection;
  private final IRepositoryTreeModel repositoryModel;
  private final IRepositoryTreeView treeView;
  private final String rootKey;
  private final List<AgnosticTreeNode> typeNodes = new ArrayList<>();

  public RepositoryTreePresenter(
          Environment environment,
          IApplicationModel model, ItemTypeCollection itemTypeCollection, IRepositoryTreeModel repositoryModel,
          IRepositoryTreeView treeView,
          String rootKey) {
    this.environment = environment;
    this.model = model;
    this.itemTypeCollection = itemTypeCollection;
    this.repositoryModel = repositoryModel;
    this.treeView = treeView;
    this.rootKey = rootKey;
    repositoryModel.addRepositoryTreeModelListener(new IRepositoryTreeModelListener() {
      @Override
      public void printNameFileAdded(PrintNameFile file) {
        addPrintNameFileToTree(file);
      }

      @Override
      public void printNameFileRemoved(PrintNameFile file) {
        removePrintNameFileFromTree(file);
      }
    });
  }

  public void initPresentation() {
    AgnosticTree tree = treeView.addAgnosticTree();
    setUiConfiguration(tree);
    tree.whenSelectionChangesDo(new Closure<Object[]>() {
      @Override
      public void execute(Object[] value) {
        if (value.length == 0) {
          repositoryModel.setSelectedObject(new Object[0]);
        } else {
          repositoryModel.setSelectedObject(value);
        }
      }
    });
    AgnosticTreeNode root = tree.createRootNode(environment.getString(rootKey) + " ["
            + repositoryModel.getRepositoryPath()
            + "]");
    for (IItemType type : repositoryModel.getAllItemTypes()) {
      AgnosticTreeNode typeNode = root.addChildNode(type);
      typeNodes.add(typeNode);
      for (PrintNameFile file : repositoryModel.getPrintNameFiles(type)) {
        typeNode.addChildNode(file);
      }
    }
  }

  private void setUiConfiguration(AgnosticTree tree) {
    ItemTypePropertiesMap propertiesMap = registerItemTypePresentations(itemTypeCollection);
    ItemTreeUiConfiguration configuration = new ItemTreeUiConfiguration(environment, propertiesMap);
    tree.setUiConfiguration(configuration);
  }

  private ItemTypePropertiesMap registerItemTypePresentations(ItemTypeCollection itemTypeCollection) {
    Collection<ItemTypePresentationFactory> presentationFactories = environment.instantiateAllImplementers(ItemTypePresentationFactory.class);
    ItemTypePropertiesMap map = new ItemTypePropertiesMap();
    for (ItemTypePresentationFactory factory : presentationFactories) {
      IItemTypeViewProperties properties = factory.createItemTypeCreationProperties(model, environment);
      String itemType = factory.getClass().getAnnotation(ForItemType.class).value();
      map.put(itemTypeCollection.getById(itemType), properties);
    }
    return map;
  }

  private void addPrintNameFileToTree(PrintNameFile file) {
    for (AgnosticTreeNode typeNode : typeNodes) {
      if (typeNode.getObject().equals(file.getItemType())) {
        typeNode.addChildNode(file);
      }
    }
  }

  private void removePrintNameFileFromTree(PrintNameFile file) {
    for (AgnosticTreeNode typeNode : typeNodes) {
      if (typeNode.getObject().equals(file.getItemType())) {
        for (AgnosticTreeNode childNode : typeNode.getChildren()) {
          if (childNode.getObject().equals(file)) {
            childNode.remove();
            return;
          }
        }
      }
    }
  }
}