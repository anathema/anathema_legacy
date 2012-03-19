package net.sf.anathema.character.equipment.impl.creation.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class WeaponDamageView implements IWeaponDamageView {

  private final JLabel damageLabel = new JLabel();
  private final JLabel minDamageLabel = new JLabel();
  private final IntegerSpinner damageValueSpinner = new IntegerSpinner(0);
  private final IntegerSpinner minDamageValueSpinner = new IntegerSpinner(0);
  private final IChangeableJComboBox<HealthType> typeBox = new ChangeableJComboBox<HealthType>(new HealthType[0], false);

  public void fillInto(JPanel panel, int columnCount) {
    panel.add(damageLabel);
    panel.add(damageValueSpinner.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(typeBox.getComponent(), GridDialogLayoutDataFactory.createHorizontalSpanData(
        columnCount - 2,
        GridDialogLayoutData.FILL_HORIZONTAL));
    panel.add(minDamageLabel);
    panel.add(minDamageValueSpinner.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
  }

  public void setEnabled(boolean enabled) {
    throw new NotYetImplementedException();
  }

  public int getColumnCount() {
    return 3;
  }

  public IntegerSpinner getDamageIntegerSpinner() {
    return damageValueSpinner;
  }
  
  public IntegerSpinner getMinDamageIntegerSpinner() {
	return minDamageValueSpinner;
  }

  public void setHealthTypeRenderer(ListCellRenderer renderer) {
    typeBox.setRenderer(renderer);
  }

  public void setDamageLabelText(String labelText) {
    this.damageLabel.setText(labelText);
  }
  
  public void setMinDamageLabelText(String labelText) {
	this.minDamageLabel.setText(labelText);
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