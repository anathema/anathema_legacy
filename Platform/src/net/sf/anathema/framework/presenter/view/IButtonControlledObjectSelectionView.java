package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IButtonControlledObjectSelectionView extends IObjectSelectionView {

  public void setButtonEnabled(boolean enabled);

  public void addButtonListener(IObjectValueChangedListener listener);
}