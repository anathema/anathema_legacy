package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.control.ObjectValueListener;

public class NullObjectSelectionView<V> implements IObjectSelectionView<V> {
  @Override
  public void setSelectedObject(V object) {
    //Nothing to do
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    //Nothing to do
  }

  @Override
  public void setObjects(V[] objects) {
    //Nothing to do
  }

  @Override
  public V getSelectedObject() {
    return null;
  }

  @Override
  public boolean isObjectSelected() {
    return false;
  }

  @Override
  public void setEnabled(boolean enabled) {
    //Nothing to do
  }
}