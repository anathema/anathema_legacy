package net.sf.anathema.character.view;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IChangeListener;

public interface ISpecialtyView extends IIntValueView {

  void addDeleteListener(IChangeListener listener);

  void delete();

  void setDeleteButtonEnabled(boolean enabled);
}