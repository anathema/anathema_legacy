package net.sf.anathema.lib.gui.selection;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;

public class ObjectSelectionView implements IObjectSelectionView, IGridDialogPanelContent {

  private final IChangeableJComboBox comboBox;
  private final JLabel label;

  public JComboBox getComboBox() {
    return comboBox.getComponent();
  }

  public ObjectSelectionView(String labelString, ListCellRenderer renderer, Object[] objects) {
    this(labelString, renderer, objects, false);
  }

  public ObjectSelectionView(String labelString, ListCellRenderer renderer, Object[] objects, boolean editable) {
    this.label = new JLabel(labelString);
    this.comboBox = new ChangeableJComboBox(objects, editable);
    comboBox.setRenderer(renderer);
    setSelectedObject(null);
  }

  public void addComponents(IGridDialogPanel dialogPanel) {
    addComponents(dialogPanel, new GridDialogLayoutData());
  }

  public void addComponents(IGridDialogPanel panel, final IGridDialogLayoutData selectionData) {
    panel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel layoutPanel, int columnCount) {
        layoutPanel.add(label);
        layoutPanel.add(comboBox.getComponent(), selectionData);
      }
    });
  }

  /** Requires two colums */
  public void addTo(JPanel panel, IGridDialogLayoutData data) {
    panel.add(label);
    panel.add(comboBox.getComponent(), data);
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

  public void setEnabled(boolean enabled) {
    label.setEnabled(enabled);
    comboBox.getComponent().setEnabled(enabled);
  }

  public Object getSelectedObject() {
    return getComboBox().getSelectedItem();
  }
}