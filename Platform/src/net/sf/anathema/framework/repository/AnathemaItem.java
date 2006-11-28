package net.sf.anathema.framework.repository;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class AnathemaItem implements IItem {

  private String printName;
  private final IItemType itemType;
  private final RepositoryLocation repositoryLocation;
  private final IIdentificate identificate;
  private final GenericControl<IItemListener> repositoryItemListeners = new GenericControl<IItemListener>();
  private final IItemData itemData;

  public void addItemListener(IItemListener listener) {
    repositoryItemListeners.addListener(listener);
  }

  public void removeItemListener(IItemListener listener) {
    repositoryItemListeners.removeListener(listener);
  }

  private void firePrintNameChanged(final String name) {
    repositoryItemListeners.forAllDo(new IClosure<IItemListener>() {
      public void execute(IItemListener input) {
        input.printNameChanged(name);
      }
    });
  }

  public AnathemaItem(IItemType type, IItemData itemData) {
    Ensure.ensureArgumentTrue("Use second constructor for nonpersisted items.", type.supportsRepository()); //$NON-NLS-1$
    this.itemType = type;
    this.repositoryLocation = new RepositoryLocation(this);
    this.identificate = repositoryLocation;
    this.itemData = itemData;
    initItemData();
  }

  public AnathemaItem(IItemType type, IIdentificate identificate, IItemData itemData) {
    this.itemType = type;
    this.repositoryLocation = null;
    this.identificate = identificate;
    this.itemData = itemData;
    initItemData();
  }

  private void initItemData() {
    if (itemData != null) {
      itemData.setPrintNameAdjuster(new PrintNameAdjuster(this));
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