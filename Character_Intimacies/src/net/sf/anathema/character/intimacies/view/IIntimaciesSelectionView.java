package net.sf.anathema.character.intimacies.view;

import java.awt.event.ActionListener;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IIntimaciesSelectionView {

  public void addTextChangeListener(IObjectValueChangedListener<String> listener);

  public void addAddButtonListener(ActionListener listener);

  public void setAddButtonEnabled(boolean complete);

  public void setName(String name);
}