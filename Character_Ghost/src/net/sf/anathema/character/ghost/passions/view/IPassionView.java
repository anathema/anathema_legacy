package net.sf.anathema.character.ghost.passions.view;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IChangeListener;

public interface IPassionView extends IIntValueView {

  void addDeleteListener(IChangeListener listener);

  void delete();

  void setDeleteButtonEnabled(boolean enabled);
}