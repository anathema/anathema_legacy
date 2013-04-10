package net.sf.anathema.platform.fx;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public class ObjectSelectionView<V> implements IObjectSelectionView<V> {
  @Override
  public void setSelectedObject(V object) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setObjects(V[] objects) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public V getSelectedObject() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean isObjectSelected() {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setEnabled(boolean enabled) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
