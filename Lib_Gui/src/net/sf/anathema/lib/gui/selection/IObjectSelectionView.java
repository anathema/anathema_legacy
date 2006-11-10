package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IObjectSelectionView<V> {

  public void setSelectedObject(V object);

  public void addObjectSelectionChangedListener(IObjectValueChangedListener<V> listener);

  public void setObjects(V[] objects);

  public V getSelectedObject();

  public boolean isObjectSelected();

  public void setEnabled(boolean enabled);
}