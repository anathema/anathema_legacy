package net.sf.anathema.framework.repository;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.ObjectUtilities;
import org.jmock.example.announcer.Announcer;

public abstract class AbstractAnathemaItem implements IItem {

  private String printName;
  private final IItemType itemType;
  private final RepositoryLocation repositoryLocation;
  private final IIdentificate identificate;
  private final Announcer<IItemListener> repositoryItemListeners = Announcer.to(IItemListener.class);

  public AbstractAnathemaItem(IItemType type) {
    Preconditions.checkArgument(type.supportsRepository(), "Use second constructor for non-persisted items."); //$NON-NLS-1$
    this.itemType = type;
    this.repositoryLocation = new RepositoryLocation(this);
    this.identificate = new IIdentificate() {
      @Override
      public String getId() {
        return repositoryLocation.getId();
      }
    };
  }

  public AbstractAnathemaItem(IItemType type, IIdentificate identificate) {
    this.itemType = type;
    this.repositoryLocation = null;
    this.identificate = identificate;
  }

  @Override
  public void addItemListener(IItemListener listener) {
    repositoryItemListeners.addListener(listener);
  }

  @Override
  public void removeItemListener(IItemListener listener) {
    repositoryItemListeners.removeListener(listener);
  }

  private void firePrintNameChanged(final String name) {
    repositoryItemListeners.announce().printNameChanged(name);
  }

  @Override
  public final IItemType getItemType() {
    return itemType;
  }

  @Override
  public final synchronized String getId() {
    return identificate.getId();
  }

  @Override
  public String getDisplayName() {
    return printName == null ? DEFAULT_PRINT_NAME : printName;
  }

  @Override
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

  @Override
  public IItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }

  @Override
  public String toString() {
    return getItemType() + ": " + getDisplayName(); //$NON-NLS-1$
  }
}