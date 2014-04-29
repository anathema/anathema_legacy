package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.WeaponDamageView;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IIntegerSpinner;
import net.sf.anathema.lib.gui.widgets.SwingIntegerSpinner;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingWeaponDamageView implements WeaponDamageView {

  private final JLabel damageLabel = new JLabel();
  private final JLabel minDamageLabel = new JLabel();
  private final SwingIntegerSpinner damageValueSpinner = new SwingIntegerSpinner(0);
  private final SwingIntegerSpinner minDamageValueSpinner = new SwingIntegerSpinner(0);
  private final IChangeableJComboBox<HealthType> typeBox = new ChangeableJComboBox<>(new HealthType[0]);

  @Override
  public void addTo(JPanel panel) {
    panel.add(damageLabel);
    panel.add(damageValueSpinner.getComponent(), new CC().growX());
    panel.add(typeBox.getComponent(), new CC().growX().spanX());
    panel.add(minDamageLabel);
    panel.add(minDamageValueSpinner.getComponent(), new CC().growX());
  }

  @Override
  public void setEnabled(boolean enabled) {
    throw new NotYetImplementedException();
  }

  @Override
  public IIntegerSpinner getDamageIntegerSpinner() {
    return damageValueSpinner;
  }

  @Override
  public IIntegerSpinner getMinDamageIntegerSpinner() {
    return minDamageValueSpinner;
  }

  @Override
  public void setHealthTypeRenderer(AgnosticUIConfiguration<HealthType> renderer) {
    typeBox.setRenderer(new ConfigurableListCellRenderer(renderer));
  }

  @Override
  public void setDamageLabelText(String labelText) {
    this.damageLabel.setText(labelText);
  }

  @Override
  public void setMinDamageLabelText(String labelText) {
    this.minDamageLabel.setText(labelText);
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<HealthType> listener) {
    typeBox.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void removeObjectSelectionChangedListener(ObjectValueListener<HealthType> listener) {
    typeBox.removeObjectSelectionChangeListener(listener);
  }

  @Override
  public void setObjects(HealthType[] objects) {
    typeBox.setObjects(objects);
  }

  @Override
  public void setSelectedObject(HealthType object) {
    typeBox.setSelectedObject(object);
  }

  @Override
  public HealthType getSelectedObject() {
    return typeBox.getSelectedObject();
  }

  @Override
  public boolean isObjectSelected() {
    return getSelectedObject() != null;
  }
}