package net.sf.anathema.framework.repository;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.lang.StringUtilities;

public class RepositoryLocation implements IItemRepositoryLocation {

  private final IItem item;
  private String id;

  public RepositoryLocation(IItem item) {
    this.item = item;
  }

  @Override
  public String getIdProposal() {
    return StringUtilities.getFileNameRepresentation(item.getDisplayName());
  }

  @Override
  public synchronized void setId(String id) {
    Preconditions.checkArgument(this.id == null, "Item's id must not be changed."); //$NON-NLS-1$
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