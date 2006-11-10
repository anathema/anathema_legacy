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

  public String getIdProposal() {
    return AnathemaStringUtilities.getFileNameRepresentation(item.getDisplayName());
  }

  public synchronized void setId(String id) {
    Ensure.ensureNull("Item's id must not be changed.", this.id); //$NON-NLS-1$
    this.id = id;
  }

  public synchronized String getId() {
    return id;
  }

  public IItemType getItemType() {
    return item.getItemType();
  }
}