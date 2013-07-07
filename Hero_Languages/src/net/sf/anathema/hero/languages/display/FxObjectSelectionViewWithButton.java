package net.sf.anathema.hero.languages.display;

import net.sf.anathema.lib.control.ObjectValueListener;

public class FxObjectSelectionViewWithButton<V> implements IButtonControlledObjectSelectionView<V> {
  @Override
  public void setButtonEnabled(boolean enabled) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addButtonListener(ObjectValueListener<V> listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setSelectedObject(V object) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void removeObjectSelectionChangedListener(ObjectValueListener<V> listener) {
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