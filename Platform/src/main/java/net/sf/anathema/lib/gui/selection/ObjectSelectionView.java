package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.control.ObjectValueListener;

import java.util.Collection;

public interface ObjectSelectionView<V> {

  void setSelectedObject(V object);

  void addObjectSelectionChangedListener(ObjectValueListener<V> listener);

  void removeObjectSelectionChangedListener(ObjectValueListener<V> listener);

  void setObjects(V[] objects);

  void setObjects(Collection<V> objects);

  V getSelectedObject();

  void setEnabled(boolean enabled);

  void clearSelection();
}