package net.sf.anathema.framework.repository;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class AnathemaItem implements IItem {

  private String printName;
  protected final IItemType itemType;
  private final RepositoryLocation repositoryLocation;
  private final IIdentificate identificate;
  private final List<IItemListener> repositoryItemListeners = new ArrayList<IItemListener>();
  private IItemData itemData;

  public synchronized void addItemListener(IItemListener listener) {
    repositoryItemListeners.add(listener);
  }

  public synchronized void removeItemListener(IItemListener listener) {
    repositoryItemListeners.remove(listener);
  }

  private synchronized void firePrintNameChanged(String name) {
    List<IItemListener> clonedListeners = new ArrayList<IItemListener>(repositoryItemListeners);
    for (IItemListener listener : clonedListeners) {
      listener.printNameChanged(name);
    }
  }

  public AnathemaItem(IItemType type, IItemData itemData) {
    this.itemType = type;
    this.repositoryLocation = type.supportsRepository() ? new RepositoryLocation(this) : null;
    this.identificate = repositoryLocation;
    initItemData(itemData);
  }

  public AnathemaItem(IItemType type, IIdentificate identificate, IItemData itemData) {
    this.itemType = type;
    this.repositoryLocation = null;
    this.identificate = identificate;
    initItemData(itemData);
  }

  private void initItemData(IItemData data) {
    this.itemData = data;
    if (data != null) {
      data.setPrintNameAdjuster(new PrintNameAdjuster(this));
    }
  }

  public final IItemType getItemType() {
    return itemType;
  }

  public final synchronized String getId() {
    return identificate.getId();
  }

  public String getIdProposal() {
    return printName == null ? getItemType().getId() + DEFAULT_PRINT_NAME : printName;
  }

  public String getDisplayName() {
    return printName == null ? DEFAULT_PRINT_NAME : printName;
  }

  public void setPrintName(String printName) {
    if (StringUtilities.isNullOrEmpty(printName)) {
      printName = null;
    }
    if (ObjectUtilities.equals(this.printName, printName)) {
      return;
    }
    this.printName = printName;
    firePrintNameChanged(getDisplayName());
  }

  public IItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }

  @Override
  public String toString() {
    return getItemType() + ": " + getDisplayName(); //$NON-NLS-1$
  }

  public IItemData getItemData() {
    return itemData;
  }

  public boolean isDirty() {
    return itemData != null && itemData.isDirty();
  }

  public void setClean() {
    if (itemData != null) {
      itemData.setClean();
    }
  }

  public void addDirtyListener(IChangeListener changeListener) {
    if (itemData != null) {
      itemData.addDirtyListener(changeListener);
    }
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    if (itemData != null) {
      itemData.removeDirtyListener(changeListener);
    }
  }
}