package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.SwingItemView;
import net.sf.anathema.lib.exception.AnathemaException;

public interface SwingItemViewFactory extends ItemViewFactory{

  SwingItemView createView(IItem item) throws AnathemaException;
}