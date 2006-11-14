package net.sf.anathema.campaign.concrete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.campaign.model.ISeriesContentModel;
import net.sf.anathema.campaign.module.SeriesTypeFilter;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeModelListener;
import net.sf.anathema.framework.repository.tree.RepositoryTreeModel;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class SeriesContentModel implements ISeriesContentModel {

  public static ItemType[] createSupportedItemTypes(IItemTypeRegistry itemTypes) {
    return RepositoryTreeModel.createPersistableItemTypes(itemTypes, new SeriesTypeFilter());
  }

  private final Set<IItemType> types = new HashSet<IItemType>();
  private final Set<PrintNameFile> items = new ListOrderedSet<PrintNameFile>();
  private final GenericControl<IRepositoryTreeModelListener> listeners = new GenericControl<IRepositoryTreeModelListener>();

  public SeriesContentModel(IItemType[] contentTypes) {
    for (IItemType itemType : contentTypes) {
      addType(itemType);
    }
  }

  public IItemType[] getAllItemTypes() {
    return types.toArray(new IItemType[types.size()]);
  }

  public PrintNameFile[] getPrintNameFiles(IItemType itemType) {
    List<PrintNameFile> filesOfType = new ArrayList<PrintNameFile>();
    for (PrintNameFile file : items) {
      if (file.getItemType() == itemType) {
        filesOfType.add(file);
      }
    }
    return filesOfType.toArray(new PrintNameFile[filesOfType.size()]);
  }

  public void addItem(PrintNameFile file) {
    if (!items.add(file)) {
      return;
    }
    if (!types.contains(file.getItemType())) {
      return;
    }
    firePrintNameFileAdded(file);
  }

  private void addType(IItemType type) {
    if (types.add(type)) {
      fireTypeAdded(type);
    }
  }

  private void fireTypeAdded(final IItemType type) {
    listeners.forAllDo(new IClosure<IRepositoryTreeModelListener>() {
      public void execute(IRepositoryTreeModelListener input) {
        input.itemTypeAdded(type);
      }
    });
  }

  private void firePrintNameFileAdded(final PrintNameFile file) {
    listeners.forAllDo(new IClosure<IRepositoryTreeModelListener>() {
      public void execute(IRepositoryTreeModelListener input) {
        input.printNameFileAdded(file);
      }
    });
  }

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener) {
    listeners.addListener(listener);
  }

  public void removeItem(PrintNameFile file) {
    if (!items.remove(file)) {
      return;
    }
    firePrintNameFileRemoved(file);
  }

  private void firePrintNameFileRemoved(final PrintNameFile item) {
    listeners.forAllDo(new IClosure<IRepositoryTreeModelListener>() {
      public void execute(IRepositoryTreeModelListener input) {
        input.printNameFileRemoved(item);
      }
    });
  }
}