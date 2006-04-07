package net.sf.anathema.character.library.removableentry.presenter;

import java.awt.event.ActionListener;

public interface IRemovableEntryView {

  public void addButtonListener(ActionListener listener);

  public void setButtonEnabled(boolean enabled);

  public void delete();
}