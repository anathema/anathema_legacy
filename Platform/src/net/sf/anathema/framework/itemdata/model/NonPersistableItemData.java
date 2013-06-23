package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.framework.repository.NullChangeManagement;

public abstract class NonPersistableItemData implements ItemData {

  @Override
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    // nothing to do;
  }

  @Override
  public ChangeManagement getChangeManagement() {
    return new NullChangeManagement();
  }
}