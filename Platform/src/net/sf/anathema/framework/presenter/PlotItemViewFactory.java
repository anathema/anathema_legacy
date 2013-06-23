package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.view.ItemView;
import net.sf.anathema.lib.exception.AnathemaException;

public interface PlotItemViewFactory {

  ItemView createView(Item item) throws AnathemaException;
}