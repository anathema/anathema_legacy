package net.sf.anathema.framework.presenter.view;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;

public class ObjectSelectionView implements IObjectSelectionView {

  private final IChangeableJComboBox<Object> comboBox;

  public JComboBox getComboBox() {
    return comboBox.getComponent();
  }

  public ObjectSelectionView(Object[] objects) {
    this(objects, false);
  }

  public ObjectSelectionView(Object[] objects, boolean editable) {
    this.comboBox = new ChangeableJComboBox<Object>(objects, editable);
    setSelectedObject(null);
  }

  public void addTo(final String labelString, final ListCellRenderer renderer, IGridDialogPanel panel) {
    addTo(labelString, renderer, panel, new GridDialogLayoutData());
  }

  public void addTo(
      final String labelString,
      final ListCellRenderer renderer,
      IGridDialogPanel panel,
      final IGridDialogLayoutData selectionData) {
    panel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel layoutPanel, int columnCount) {
        layoutPanel.add(new JLabel(labelString));
        comboBox.setRenderer(renderer);
        layoutPanel.add(comboBox.getComponent(), selectionData);
      }
    });
  }

  public void setSelectedObject(Object object) {
    comboBox.setSelectedObject(object);
  }

  public void setObjects(Object[] objects) {
    comboBox.setObjects(objects);
  }

  public void addObjectSelectionChangedListener(final IObjectValueChangedListener listener) {
    comboBox.addObjectSelectionChangedListener(listener);
  }
}