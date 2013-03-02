package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.repository.IBasicRepositoryIdData;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepositoryTreeModel implements IRepositoryTreeModel {

  private final IItemType[] integratedItemTypes;
  private final Announcer<IRepositoryTreeModelListener> control = Announcer.to(IRepositoryTreeModelListener.class);
  private final Announcer<IChangeListener> changeControl = Announcer.to(IChangeListener.class);
  private final IItemManagementModel itemManagementModel;
  private final IRepository repository;
  private final IItemTypeRegistry itemTypes;
  private Object[] currentlySelectedUserObjects;
  private final RepositoryFileAccessFactory repositoryFileAccessFactory;

  public RepositoryTreeModel(IRepository repository, IItemManagementModel itemManagementModel, IItemTypeRegistry itemTypes) {
    this.repository = repository;
    this.itemManagementModel = itemManagementModel;
    this.itemTypes = itemTypes;
    this.integratedItemTypes = createIntegratedItemTypes(itemTypes);
    this.repositoryFileAccessFactory = new RepositoryFileAccessFactory(repository);
  }

  private IItemType[] createIntegratedItemTypes(IItemTypeRegistry itemTypes) {
    List<IItemType> integratedItemTypes = new ArrayList<>();
    for (IItemType itemType : itemTypes.getAllItemTypes()) {
      if (itemType.isIntegrated()) {
        integratedItemTypes.add(itemType);
      }
    }
    return integratedItemTypes.toArray(new IItemType[integratedItemTypes.size()]);
  }

  @Override
  public IItemType[] getAllItemTypes() {
    return integratedItemTypes;
  }

  @Override
  public Collection<PrintNameFile> getPrintNameFiles(IItemType itemType) {
    return repository.getPrintNameFileAccess().collectAllPrintNameFiles(itemType);
  }

  @Override
  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener) {
    control.addListener(listener);
  }

  @Override
  public boolean canSelectionBeDeleted() {
    if (currentlySelectedUserObjects.length == 0) {
      return false;
    }
    for (Object object : currentlySelectedUserObjects) {
      if (!isPrintNameFile(object)) {
        return false;
      }
      PrintNameFile file = (PrintNameFile) object;
      boolean open = itemManagementModel.isOpen(file.getRepositoryId(), file.getItemType());
      if (open) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void deleteSelection() throws RepositoryException {
    if (!canSelectionBeDeleted()) {
      return;
    }
    for (Object object : currentlySelectedUserObjects) {
      PrintNameFile file = (PrintNameFile) object;
      repository.deleteAssociatedItem(file);
      control.announce().printNameFileRemoved(file);
    }
  }

  @Override
  public String getRepositoryPath() {
    return repository.getRepositoryPath();
  }

  @Override
  public void setSelectedObject(Object[] objects) {
    this.currentlySelectedUserObjects = objects;
    changeControl.announce().changeOccurred();
  }

  @Override
  public void addTreeSelectionChangeListener(IChangeListener changeListener) {
    changeControl.addListener(changeListener);
  }

  @Override
  public PrintNameFile[] getPrintNameFilesInSelection() {
    List<PrintNameFile> files = new ArrayList<>();
    for (Object object : currentlySelectedUserObjects) {
      if (isPrintNameFile(object)) {
        files.add((PrintNameFile) object);
      }
    }
    return files.toArray(new PrintNameFile[files.size()]);
  }

  @Override
  public String createUniqueId(final IItemType type, final String id) {
    return repository.createUniqueRepositoryId(new IBasicRepositoryIdData() {
      @Override
      public String getIdProposal() {
        return id;
      }

      @Override
      public IItemType getItemType() {
        return type;
      }
    });
  }

  @Override
  public IItemType getItemTypeForId(String id) {
    return itemTypes.getById(id);
  }

  @Override
  public String getMainFilePath(IItemType type, String id) {
    return repository.getRepositoryFileResolver().getMainFile(type, id).getPath();
  }

  @Override
  public IRepositoryFileAccess getFileAccess(PrintNameFile printNameFile) {
    return repositoryFileAccessFactory.getFileAccess(printNameFile);
  }

  private boolean isPrintNameFile(Object object) {
    return object instanceof PrintNameFile;
  }

  @Override
  public IRepositoryWriteAccess getWriteAccess(IItemType type, String id) throws RepositoryException {
    return repository.createWriteAccess(type, id);
  }

  @Override
  public void refreshItem(IItemType type, String id) {
    control.announce().printNameFileAdded(repository.getPrintNameFileAccess().getPrintNameFile(type, id));
    repository.refresh();
  }
}