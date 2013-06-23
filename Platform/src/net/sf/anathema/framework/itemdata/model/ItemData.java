package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.ChangeManagement;

public interface ItemData extends ChangeManagement {

  void setPrintNameAdjuster(PrintNameAdjuster adjuster);
}