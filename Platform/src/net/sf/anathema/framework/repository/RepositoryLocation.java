package net.sf.anathema.framework.repository;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;

public class RepositoryLocation implements IItemRepositoryLocation {

  private final IItem item;
  private String id;

  public RepositoryLocation(IItem item) {
    this.item = item;
  }

  @Override
  public String getIdProposal() {
    return AnathemaStringUtilities.getFileNameRepresentation(item.getDisplayName());
  }

  @Override
  public synchronized void setId(String id) {
    Ensure.ensureNull("Item's id must not be changed.", this.id); //$NON-NLS-1$
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