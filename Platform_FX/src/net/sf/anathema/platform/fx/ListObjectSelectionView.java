package net.sf.anathema.platform.fx;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.list.veto.IVetor;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;

public class ListObjectSelectionView<T> implements IVetoableObjectSelectionView<T> {
  @Override
  public void addSelectionVetor(IVetor vetor) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void removeSelectionVetor(IVetor vetor) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setSelectedObject(T object) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<T> listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setObjects(T[] objects) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public T getSelectedObject() {
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