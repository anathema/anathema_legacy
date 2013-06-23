package net.sf.anathema.character.item;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.ItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.framework.repository.IItemListener;
import net.sf.anathema.framework.repository.IItemRepositoryLocation;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.repository.RepositoryLocation;
import net.sf.anathema.lib.util.Identifier;
import org.jmock.example.announcer.Announcer;

public class DataItem implements Item {

  private final static String NOT_SET = null;
  private final ItemData itemData;
  private final IItemType itemType;
  private final RepositoryLocation repositoryLocation;
  private final Identifier identificate;
  private final Announcer<IItemListener> repositoryItemListeners = Announcer.to(IItemListener.class);
  private String printName;

  public DataItem(IItemType type, ItemData itemData) {
    Preconditions.checkArgument(type.supportsRepository());
    Preconditions.checkNotNull(itemData);
    this.itemType = type;
    this.repositoryLocation = new RepositoryLocation(this);
    this.identificate = new Identifier() {
      @Override
      public String getId() {
        return repositoryLocation.getId();
      }
    };
    this.itemData = itemData;
    itemData.setPrintNameAdjuster(new PrintNameAdjuster(this));
  }

  @Override
  public ItemData getItemData() {
    return itemData;
  }

  @Override
  public void addItemListener(IItemListener listener) {
    repositoryItemListeners.addListener(listener);
  }

  @Override
  public void removeItemListener(IItemListener listener) {
    repositoryItemListeners.removeListener(listener);
  }

  private void firePrintNameChanged(String name) {
    repositoryItemListeners.announce().printNameChanged(name);
  }

  @Override
  public final IItemType getItemType() {
    return itemType;
  }

  @Override
  public ChangeManagement getChangeManagement() {
    return itemData.getChangeManagement();
  }

  @Override
  public final synchronized String getId() {
    return identificate.getId();
  }

  @Override
  public String getDisplayName() {
    return printName == NOT_SET ? DEFAULT_PRINT_NAME : printName;
  }

  @Override
  public void setPrintName(String printName) {
    if (Strings.isNullOrEmpty(printName)) {
      printName = NOT_SET;
    }
    if (Objects.equal(this.printName, printName)) {
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
    return getItemType() + ": " + getDisplayName();
  }
}