package net.sf.anathema.framework.repository;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class AnathemaDataItem extends AbstractAnathemaItem {

  private final IItemData itemData;

  public AnathemaDataItem(IItemType type, IItemData itemData) {
    super(type);
    Ensure.ensureArgumentTrue("Use second constructor for nonpersisted items.", type.supportsRepository()); //$NON-NLS-1$
    Ensure.ensureArgumentNotNull("Use AnathemaNullDataItem for items without data.", itemData); //$NON-NLS-1$
    this.itemData = itemData;
    itemData.setPrintNameAdjuster(new PrintNameAdjuster(this));
  }

  public AnathemaDataItem(IItemType type, IIdentificate identificate, IItemData itemData) {
    super(type, identificate);
    Ensure.ensureArgumentNotNull("Use AnathemaNullDataItem for items without data.", itemData); //$NON-NLS-1$
    this.itemData = itemData;
    itemData.setPrintNameAdjuster(new PrintNameAdjuster(this));
  }

  public IItemData getItemData() {
    return itemData;
  }

  public boolean isDirty() {
    return itemData.isDirty();
  }

  public void setClean() {
    itemData.setClean();
  }

  public void addDirtyListener(IChangeListener changeListener) {
    itemData.addDirtyListener(changeListener);
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    itemData.removeDirtyListener(changeListener);
  }
}