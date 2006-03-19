package net.sf.anathema.character.view.basic;

import net.sf.anathema.lib.control.objectvalue.ITwoObjectsValueChangedListener;

public interface IButtonControlledComboEditView {

  public void setSelectedObject(Object object);

  public void addObjectSelectionChangedListener(ITwoObjectsValueChangedListener listener);

  public void setText(String label);
}