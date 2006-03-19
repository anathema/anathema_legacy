package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IObjectSelectionView {
  
  public void setSelectedObject(Object object);

  public void addObjectSelectionChangedListener(IObjectValueChangedListener listener);

  public void setObjects(Object[] objects); 
}