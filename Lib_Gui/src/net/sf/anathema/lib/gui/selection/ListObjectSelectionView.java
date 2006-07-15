package net.sf.anathema.lib.gui.selection;

import javax.swing.JComponent;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.list.SmartJList;

public class ListObjectSelectionView<V> implements IListObjectSelectionView<V> {

  private final SmartJList<V> smartList;
  private final Class< ? extends V> contentClazz;

  public ListObjectSelectionView(Class< ? extends V> contentClazz) {
    this.contentClazz = contentClazz;
    this.smartList = new SmartJList<V>(contentClazz);
  }

  public void setCellRenderer(ListCellRenderer renderer) {
    smartList.setCellRenderer(renderer);

  }

  public void addObjectSelectionChangedListener(final IObjectValueChangedListener<V> listener) {
    smartList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

      public void valueChanged(ListSelectionEvent e) {
        listener.valueChanged(smartList.getSelectedValue());
      }
    });
  }

  public void setObjects(V[] objects) {
    smartList.setObjects(objects);
  }

  public void setSelectedObject(V object) {
    V[] values = ArrayUtilities.transform(new Object[] { object }, contentClazz);
    smartList.setSelectedObjects(values);
  }
  
  public JComponent getContent() {
    return smartList;
  }
}