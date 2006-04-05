package net.sf.anathema.character.library.removableentry.presenter;

import java.awt.event.ActionListener;

public interface IRemovableEntryView {

  public void addRemoveButtonListener(ActionListener listener);

  public void setDeleteEnabled(boolean enabled);

  public void delete();
}