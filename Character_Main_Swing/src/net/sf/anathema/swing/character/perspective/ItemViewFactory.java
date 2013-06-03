package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.swing.IView;

public interface ItemViewFactory {
  IView createView(IItem item);
}
