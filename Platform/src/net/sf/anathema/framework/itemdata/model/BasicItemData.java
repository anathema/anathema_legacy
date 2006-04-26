package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;

public class BasicItemData implements IItemData, IBasicItemData  {

  private IItemDescription description = new ItemDescription();

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    description.getName().addTextChangedListener(adjuster);
  }

  public IItemDescription getDescription() {
    return description ;
  }
}