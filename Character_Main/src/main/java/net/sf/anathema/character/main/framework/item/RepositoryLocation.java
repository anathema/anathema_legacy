package net.sf.anathema.character.main.framework.item;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.lang.StringUtilities;

public class RepositoryLocation implements ItemRepositoryLocation {

  private final Item item;
  private String id;

  public RepositoryLocation(Item item) {
    this.item = item;
  }

  @Override
  public String getIdProposal() {
    return StringUtilities.getFileNameRepresentation(item.getDisplayName());
  }

  @Override
  public synchronized void setId(String id) {
    Preconditions.checkArgument(this.id == null, "Item's id must not be changed.");
    this.id = id;
  }

  @Override
  public synchronized String getId() {
    return id;
  }

  @Override
  public IItemType getItemType() {
    return item.getItemType();
  }
}