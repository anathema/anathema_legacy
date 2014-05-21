package net.sf.anathema.character.framework.item;

import com.google.common.base.Preconditions;

public class SimpleRepositoryLocation implements ItemRepositoryLocation {

  private String id;

  @Override
  public synchronized void setId(String id) {
    Preconditions.checkArgument(this.id == null, "Hero's id must not be changed.");
    this.id = id;
  }

  @Override
  public synchronized String getId() {
    return id;
  }

  @Override
  public boolean requiresId() {
    return id == null;
  }
}