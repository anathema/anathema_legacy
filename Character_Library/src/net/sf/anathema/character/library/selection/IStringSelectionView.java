package net.sf.anathema.character.library.selection;

import java.awt.event.ActionListener;

import net.sf.anathema.lib.control.ObjectValueListener;

public interface IStringSelectionView {

  public void addTextChangeListener(ObjectValueListener<String> listener);

  public void addAddButtonListener(ActionListener listener);

  public void setAddButtonEnabled(boolean enabled);

  public void clear();
}