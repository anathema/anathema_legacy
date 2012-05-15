package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.control.ObjectValueListener;

public interface IObjectSelectionView<V> {

  void setSelectedObject(V object);

  void addObjectSelectionChangedListener(ObjectValueListener<V> listener);

  void setObjects(V[] objects);

  V getSelectedObject();

  boolean isObjectSelected();

  void setEnabled(boolean enabled);
}