package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.framework.repository.ChangeManagement;

public interface ItemData {

  void setPrintNameAdjuster(PrintNameAdjuster adjuster);

  ChangeManagement getChangeManagement();
}