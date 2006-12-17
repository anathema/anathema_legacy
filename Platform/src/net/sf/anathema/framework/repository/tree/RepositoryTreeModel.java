package net.sf.anathema.framework.repository.tree;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.GenericControl;

public class RepositoryTreeModel implements IRepositoryTreeModel {

  private final IItemType[] repositoryItemTypes;
  private final IPrintNameFileAccess printNameFileAccess;
  private final GenericControl<IRepositoryTreeModelListener> control = new GenericControl<IRepositoryTreeModelListener>();
  private final IItemMangementModel itemMangementModel;

  public RepositoryTreeModel(
      IPrintNameFileAccess access,
      IItemMangementModel itemMangementModel,
      IItemTypeRegistry itemTypes) {
    this.printNameFileAccess = access;
    this.itemMangementModel = itemMangementModel;
    this.repositoryItemTypes = createPersistableItemTypes(itemTypes);
  }

  private ItemType[] createPersistableItemTypes(IItemTypeRegistry itemTypes) {
    List<IItemType> persistableItemTypes = new ArrayList<IItemType>();
    for (IItemType itemType : itemTypes.getAllItemTypes()) {
      if (itemType.supportsRepository()) {
        persistableItemTypes.add(itemType);
      }
    }
    return persistableItemTypes.toArray(new ItemType[persistableItemTypes.size()]);
  }

  public IItemType[] getAllItemTypes() {
    return repositoryItemTypes;
  }

  public PrintNameFile[] getPrintNameFiles(IItemType itemType) {
    return printNameFileAccess.collectPrintNameFiles(itemType);
  }

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener) {
    control.addListener(listener);
  }

  public boolean canBeDeleted(Object userObject) {
    if (!(userObject instanceof PrintNameFile)) {
      return false;
    }
    PrintNameFile file = (PrintNameFile) userObject;
    return !printNameFileAccess.isFileOpen(file, itemMangementModel);
  }

  public void deleteItem() {
    // TODO Auto-generated method stub
  }
}