package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;

public interface IItemViewFactory {

  IItemView createView(IItem item) throws AnathemaException;
}