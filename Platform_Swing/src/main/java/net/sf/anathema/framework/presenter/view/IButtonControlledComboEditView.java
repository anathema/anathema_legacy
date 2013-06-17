package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.control.ObjectValueListener;

import java.awt.event.ActionListener;

public interface IButtonControlledComboEditView<K> {

  void addSelectionChangedListener(ObjectValueListener<K> name);

  void addEditChangedListener(ObjectValueListener<String> name);

  //TODO: Listener
  void addButtonListener(ActionListener listener);

  void clear();

  void setButtonEnabled(boolean enabled);

  void setObjects(K[] objects);
}