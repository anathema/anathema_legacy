package net.sf.anathema.character.intimacies.view;

import java.awt.event.ActionListener;

import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;

public interface IIntimaciesSelectionView {

  public void addTextChangeListener(IStringValueChangedListener listener);

  public void addAddButtonListener(ActionListener listener);

  public void setAddButtonEnabled(boolean complete);

  public void setName(String name);
}