package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;

import java.awt.event.ActionListener;

public interface IButtonControlledComboEditView<K> {

  void addSelectionChangedListener(ObjectValueListener<K> name);

  void addEditChangedListener(ObjectValueListener<String> name);

  void whenAddButtonIsClicked(Command command);

  void clear();

  void setButtonEnabled(boolean enabled);

  void setObjects(K[] objects);
}