package net.sf.anathema.framework.presenter.view;

import java.awt.event.ActionListener;

import net.sf.anathema.lib.control.ObjectValueListener;

public interface IButtonControlledComboEditView<K> {

  public void addSelectionChangedListener(ObjectValueListener<K> name);

  public void addEditChangedListener(ObjectValueListener<String> name);

  public void addButtonListener(ActionListener listener);
  
  public void addButtonListener(final ObjectValueListener<K> listener);

  public void clear();

  public void setButtonEnabled(boolean enabled);

  public void setObjects(K[] objects);
}