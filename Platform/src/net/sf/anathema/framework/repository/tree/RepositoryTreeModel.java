package net.sf.anathema.framework.repository.tree;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.collection.IFilter;

public class RepositoryTreeModel implements IRepositoryTreeModel {

  private final IItemType[] repositoryItemTypes;
  private final IPrintNameFileAccess printNameFileAccess;

  private ItemType[] createPersistableItemTypes(IItemTypeRegistry itemTypes, IFilter<IItemType> typeFilter) {
    List<IItemType> persistableItemTypes = new ArrayList<IItemType>();
    for (IItemType itemType : itemTypes.getAllItemTypes()) {
      if (itemType.supportsRepository() && typeFilter.accept(itemType)) {
        persistableItemTypes.add(itemType);
      }
    }
    return persistableItemTypes.toArray(new ItemType[persistableItemTypes.size()]);
  }

  public RepositoryTreeModel(IPrintNameFileAccess printNameFileAccess, IItemTypeRegistry itemTypes) {
    this(printNameFileAccess, itemTypes, new IFilter<IItemType>() {
      public boolean accept(IItemType object) {
        return true;
      }
    });
  }

  public RepositoryTreeModel(
      IPrintNameFileAccess printNameFileAccess,
      IItemTypeRegistry itemTypes,
      IFilter<IItemType> typeFilter) {
    this.printNameFileAccess = printNameFileAccess;
    repositoryItemTypes = createPersistableItemTypes(itemTypes, typeFilter);
  }

  public IItemType[] getAllItemTypes() {
    return repositoryItemTypes;
  }

  public PrintNameFile[] getPrintNameFiles(IItemType itemType) {
    return printNameFileAccess.collectPrintNameFiles(itemType);
  }

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener) {
    // todo: reagieren auf gespeicherte items
  }
}