package net.sf.anathema.character.lunar.heartsblood.view;

import java.awt.event.ActionListener;

public interface IRemovableStringView {

  public void addRemoveButtonListener(ActionListener listener);

  public void setDeleteEnabled(boolean enabled);

  public void delete();
}