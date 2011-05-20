package net.sf.anathema.character.thaumaturgy.view;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IThaumaturgyMagicView extends IIntValueView {

  public void addDeleteListener(IChangeListener listener);

  public void delete();

  public void setDeleteButtonEnabled(boolean enabled);
}