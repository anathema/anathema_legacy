package net.sf.anathema.character.library.selection;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;

public interface IStringSelectionView {

  void addTextChangeListener(ObjectValueListener<String> listener);

  void addAddButtonListener(Command listener);

  void setAddButtonEnabled(boolean enabled);

  void clear();
}