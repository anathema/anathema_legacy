package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.control.ObjectValueListener;

public interface IObjectSelectionView<V> {

  public void setSelectedObject(V object);

  public void addObjectSelectionChangedListener(ObjectValueListener<V> listener);

  public void setObjects(V[] objects);

  public V getSelectedObject();

  public boolean isObjectSelected();

  public void setEnabled(boolean enabled);
}