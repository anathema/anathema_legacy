package net.sf.anathema.character.ghost.fetters.view;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IChangeListener;

public interface IFetterView extends IIntValueView {

  void addDeleteListener(IChangeListener listener);

  void delete();

  void setDeleteButtonEnabled(boolean enabled);
}