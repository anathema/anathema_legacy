package net.sf.anathema.character.equipment.impl.creation.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class WeaponDamageView implements IWeaponDamageView {

  private final JLabel label = new JLabel();
  private final IntegerSpinner valueSpinner = new IntegerSpinner(0);
  private final IChangeableJComboBox<HealthType> typeBox = new ChangeableJComboBox<HealthType>(new HealthType[0], false);

  public void fillInto(JPanel panel, int columnCount) {
    panel.add(label);
    panel.add(valueSpinner.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(typeBox.getComponent(), GridDialogLayoutDataUtilities.createHorizontalSpanData(
        columnCount - 2,
        GridDialogLayoutData.FILL_HORIZONTAL));
  }

  public void setEnabled(boolean enabled) {
    throw new NotYetImplementedException();
  }

  public int getColumnCount() {
    return 3;
  }

  public IntegerSpinner getIntegerSpinner() {
    return valueSpinner;
  }

  public void setHealthTypeRenderer(ListCellRenderer renderer) {
    typeBox.setRenderer(renderer);
  }

  public void setLabelText(String labelText) {
    this.label.setText(labelText);
  }

  public void addObjectSelectionChangedListener(IObjectValueChangedListener<HealthType> listener) {
    typeBox.addObjectSelectionChangedListener(listener);
  }

  public void setObjects(HealthType[] objects) {
    typeBox.setObjects(objects);
  }

  public void setSelectedObject(HealthType object) {
    typeBox.setSelectedObject(object);
  }

  public HealthType getSelectedObject() {
    return typeBox.getSelectedObject();
  }

  public boolean isObjectSelected() {
    return getSelectedObject() != null;
  }
}