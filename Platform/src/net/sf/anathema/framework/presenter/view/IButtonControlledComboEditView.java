package net.sf.anathema.framework.presenter.view;

import java.awt.event.ActionListener;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IButtonControlledComboEditView<K> {

  public void addSelectionChangedListener(IObjectValueChangedListener<K> name);

  public void addEditChangedListener(IObjectValueChangedListener<String> name);

  public void addButtonListener(ActionListener listener);
  
  public void addButtonListener(final IObjectValueChangedListener<K> listener);

  public void clear();

  public void setButtonEnabled(boolean enabled);

  public void setObjects(K[] objects);
}