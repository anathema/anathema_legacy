package net.sf.anathema.character.library.selection;

import net.sf.anathema.lib.control.ObjectValueListener;

import java.awt.event.ActionListener;

public interface IStringSelectionView {

  void addTextChangeListener(ObjectValueListener<String> listener);

  void addAddButtonListener(ActionListener listener);

  void setAddButtonEnabled(boolean enabled);

  void clear();
}