package net.sf.anathema.lib.gui.widgets;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IView;

public interface IChangeableJComboBox<V> extends IView {

  public JComboBox getComponent();

  public void setSelectedObject(V object);

  public V getSelectedObject();

  void setObjects(V[] objects);

  public void addObjectSelectionChangedListener(IObjectValueChangedListener<V> listener);

  public void setRenderer(ListCellRenderer renderer);

  public void removeObjectSelectionChangeListener(IObjectValueChangedListener<V> listener);

  public void setPreferredSize(Dimension preferredSize);

  public Dimension getPreferredSize();

  public void clearObjects();
}