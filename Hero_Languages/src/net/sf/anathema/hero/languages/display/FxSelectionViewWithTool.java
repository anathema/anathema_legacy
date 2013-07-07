package net.sf.anathema.hero.languages.display;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;

public class FxSelectionViewWithTool<V> implements ObjectSelectionViewWithTool<V> {
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

  @Override
  public Tool addTool() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}