package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.swing.IView;

public interface ItemViewFactory {
  IView createView(Item item);
}
