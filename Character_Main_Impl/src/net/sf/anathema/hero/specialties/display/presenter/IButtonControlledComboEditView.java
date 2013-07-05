package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;

public interface IButtonControlledComboEditView<K> {

  void addSelectionChangedListener(ObjectValueListener<K> name);

  void addEditChangedListener(ObjectValueListener<String> name);

  void whenAddButtonIsClicked(Command command);

  void clear();

  void setButtonEnabled(boolean enabled);

  void setObjects(K[] objects);
}