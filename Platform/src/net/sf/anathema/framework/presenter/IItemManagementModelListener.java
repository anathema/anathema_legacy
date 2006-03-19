package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;

public interface IItemManagementModelListener {

  public void itemAdded(IItem item) throws AnathemaException;

  public void itemSelected(IItem item);

  public void itemRemoved(IItem item);

}