package net.sf.anathema.framework.repository.tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
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
  private final IItemTypeRegistry itemTypes;
  private Object[] currentlySelectedUserObjects;

  public RepositoryTreeModel(IRepository repository, IItemMangementModel itemMangementModel, IItemTypeRegistry itemTypes) {
    this.repository = repository;
    this.itemMangementModel = itemMangementModel;
    this.itemTypes = itemTypes;
    this.repositoryItemTypes = createPersistableItemTypes();
  }

  private ItemType[] createPersistableItemTypes() {
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
    return repository.getPrintNameFileAccess().collectAllPrintNameFiles(itemType);
  }

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener) {
    control.addListener(listener);
  }

  public boolean canSelectionBeDeleted() {
    if (currentlySelectedUserObjects.length == 0) {
      return false;
    }
    for (Object object : currentlySelectedUserObjects) {
      if (!isPrintNameFile(object)) {
        return false;
      }
      PrintNameFile file = (PrintNameFile) object;
      boolean open = itemMangementModel.isOpen(file.getRepositoryId(), file.getItemType());
      if (open) {
        return false;
      }
    }
    return true;
  }

  public void deleteSelection() throws RepositoryException {
    if (!canSelectionBeDeleted()) {
      return;
    }
    for (Object object : currentlySelectedUserObjects) {
      final PrintNameFile file = (PrintNameFile) object;
      repository.deleteAssociatedItem(file);
      control.forAllDo(new IClosure<IRepositoryTreeModelListener>() {
        public void execute(IRepositoryTreeModelListener input) {
          input.printNameFileRemoved(file);
        }
      });
    }
  }

  public String getRepositoryPath() {
    return repository.getRepositoryPath();
  }

  public void setSelectedObject(Object[] objects) {
    this.currentlySelectedUserObjects = objects;
    changeControl.fireChangedEvent();
  }

  public void addTreeSelectionChangeListener(IChangeListener changeListener) {
    changeControl.addChangeListener(changeListener);
  }

  public PrintNameFile[] getPrintNameFilesInSelection() {
    List<PrintNameFile> files = new ArrayList<PrintNameFile>();
    for (Object object : currentlySelectedUserObjects) {
      if (isPrintNameFile(object)) {
        files.add((PrintNameFile) object);
      }
    }
    return files.toArray(new PrintNameFile[files.size()]);
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
    return repository.getRepositoryFileResolver().getMainFile(type, id).getPath();
  }

  public IRepositoryFileAccess getFileAccess(PrintNameFile printNameFile) {
    ConfigurableFileProvider provider = new ConfigurableFileProvider();
    provider.setFile(printNameFile.getFile());
    final IRepositoryReadAccess access = repository.openReadAccess(printNameFile.getItemType(), provider);
    return new IRepositoryFileAccess() {
      public File[] getFiles() {
        return access.getFiles();
      }

      public InputStream openInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
      }
    };
  }

  private boolean isPrintNameFile(Object object) {
    return object instanceof PrintNameFile;
  }

  public IRepositoryWriteAccess getWriteAccess(IItemType type, String id) throws RepositoryException {
    return repository.createWriteAccess(type, id);
  }

  public void refreshItem(final IItemType type, final String id) {
    control.forAllDo(new IClosure<IRepositoryTreeModelListener>() {
      public void execute(IRepositoryTreeModelListener input) {
        input.printNameFileAdded(repository.getPrintNameFileAccess().getPrintNameFile(type, id));
      }
    });
  }
}