package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;

public interface IItemManagementModelListener {

  void itemAdded(IItem item) throws AnathemaException;

  void itemSelected(IItem item);

  void itemRemoved(IItem item);
}