package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.control.ObjectValueListener;

public interface ObjectSelectionView<V> {

  void setSelectedObject(V object);

  void addObjectSelectionChangedListener(ObjectValueListener<V> listener);

  void removeObjectSelectionChangedListener(ObjectValueListener<V> listener);

  void setObjects(V[] objects);

  V getSelectedObject();

  boolean isObjectSelected();

  void setEnabled(boolean enabled);
}