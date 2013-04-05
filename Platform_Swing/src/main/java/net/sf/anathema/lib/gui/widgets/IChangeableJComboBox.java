package net.sf.anathema.lib.gui.widgets;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import java.awt.Dimension;

public interface IChangeableJComboBox<V> extends IView {

  @Override
  JComboBox getComponent();

  void setSelectedObject(V object);

  V getSelectedObject();

  void setObjects(V[] objects);

  void addObjectSelectionChangedListener(ObjectValueListener<V> listener);

  void setRenderer(ListCellRenderer renderer);

  void removeObjectSelectionChangeListener(ObjectValueListener<V> listener);

  void setPreferredSize(Dimension preferredSize);

  Dimension getPreferredSize();
}