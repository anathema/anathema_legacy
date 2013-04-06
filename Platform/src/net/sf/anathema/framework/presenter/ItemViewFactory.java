package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.ItemView;
import net.sf.anathema.lib.exception.AnathemaException;

public interface ItemViewFactory {

  ItemView createView(IItem item) throws AnathemaException;
}