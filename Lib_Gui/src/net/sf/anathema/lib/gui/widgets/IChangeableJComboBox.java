package net.sf.anathema.lib.gui.widgets;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IChangeableJComboBox {

  public JComboBox getComponent();

  public void setSelectedObject(Object object);

  public Object getSelectedObject();

  void setObjects(Object[] objects);

  public void addObjectSelectionChangedListener(IObjectValueChangedListener listener);

  public void setRenderer(ListCellRenderer renderer);

  public void removeObjectSelectionChangeListener(IObjectValueChangedListener listener);

  public void setPreferredSize(Dimension preferredSize);

  public Dimension getPreferredSize();

  public void clearObjects();
}