package net.sf.anathema.charmdatabase.presenter;

import net.sf.anathema.charmdatabase.management.ICharmDatabaseManagement;
import net.sf.anathema.lib.data.Condition;

public class DirtyCharmCondition implements Condition {
  private ICharmDatabaseManagement model;

  public DirtyCharmCondition(ICharmDatabaseManagement model) {
    this.model = model;
  }

  @Override
  public boolean isFulfilled() {
    return model.getCharmEditModel().isDirty();
  }
}