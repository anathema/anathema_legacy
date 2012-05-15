package net.sf.anathema.character.library.removableentry.presenter;

import java.awt.event.ActionListener;

public interface IRemovableEntryView {

  void addButtonListener(ActionListener listener);

  void setButtonEnabled(boolean enabled);

  void delete();
}