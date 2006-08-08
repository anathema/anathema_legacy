package net.sf.anathema.character.view.basic;

import net.sf.anathema.lib.control.objectvalue.ITwoObjectsValueChangedListener;

public interface IButtonControlledComboEditView<K> {

  public void setSelectedObject(K object);

  public void addObjectSelectionChangedListener(ITwoObjectsValueChangedListener<K, String> listener);

  public void setText(String label);
}