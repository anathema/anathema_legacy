package net.sf.anathema.framework.repository.tree;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.action.ConfigurableFileProvider;
import net.sf.anathema.framework.repository.IBasicRepositoryIdData;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class RepositoryTreeModel implements IRepositoryTreeModel {

  private final IItemType[] repositoryItemTypes;
  private final GenericControl<IRepositoryTreeModelListener> control = new GenericControl<IRepositoryTreeModelListener>();
  private final ChangeControl changeControl = new ChangeControl();
  private final IItemMangementModel itemMangementModel;
  private final IRepository repository;
  private Object currentlySelectedUserObject;
  private final IItemTypeRegistry itemTypes;

  public RepositoryTreeModel(IRepository repository, IItemMangementModel itemMangementModel, IItemTypeRegistry itemTypes) {
    this.repository = repository;
    this.itemMangementModel = itemMangementModel;
    this.itemTypes = itemTypes;
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
    return repository.getPrintNameFileAccess().collectPrintNameFiles(itemType);
  }

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener) {
    control.addListener(listener);
  }

  public boolean canSelectionBeDeleted() {
    if (!(currentlySelectedUserObject instanceof PrintNameFile)) {
      return false;
    }
    PrintNameFile file = (PrintNameFile) currentlySelectedUserObject;
    return !itemMangementModel.isOpen(file.getRepositoryId(), file.getItemType());
  }

  public void deleteItem() throws RepositoryException {
    if (!canSelectionBeDeleted()) {
      return;
    }
    final PrintNameFile file = (PrintNameFile) currentlySelectedUserObject;
    repository.deleteAssociatedItem(file);
    control.forAllDo(new IClosure<IRepositoryTreeModelListener>() {
      public void execute(IRepositoryTreeModelListener input) {
        input.printNameFileRemoved(file);
      }
    });
  }

  public String getRepositoryPath() {
    return repository.getRepositoryFolder().toString();
  }

  public void setSelectedObject(Object object) {
    this.currentlySelectedUserObject = object;
    changeControl.fireChangedEvent();
  }

  public void addTreeSelectionChangeListener(IChangeListener changeListener) {
    changeControl.addChangeListener(changeListener);
  }

  public Object getSelectedObject() {
    return currentlySelectedUserObject;
  }

  public IRepositoryReadAccess getReadAccess() {
    PrintNameFile printNameFile = ((PrintNameFile) currentlySelectedUserObject);
    ConfigurableFileProvider provider = new ConfigurableFileProvider();
    provider.setFile(printNameFile.getFile());
    return repository.openReadAccess(printNameFile.getItemType(), provider);
  }

  public String createUniqueId(final IItemType type, final String id) {
    return repository.createUniqueRepositoryId(new IBasicRepositoryIdData() {
      public String getIdProposal() {
        return id;
      }

      public IItemType getItemType() {
        return type;
      }
    });
  }

  public IItemType getItemTypeForId(String id) {
    return itemTypes.getById(id);
  }

  public String getMainFilePath(IItemType type, String id) {
    StringBuilder builder = new StringBuilder("./"); //$NON-NLS-1$
    builder.append(type.getRepositoryConfiguration().getFolderName());

    return builder.toString();
  }
}