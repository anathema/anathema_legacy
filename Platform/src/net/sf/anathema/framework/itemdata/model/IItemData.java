package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.IChangeManagement;

public interface IItemData extends IChangeManagement {

  void setPrintNameAdjuster(PrintNameAdjuster adjuster);
}