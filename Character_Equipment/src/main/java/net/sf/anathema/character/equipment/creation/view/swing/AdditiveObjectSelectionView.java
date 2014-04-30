package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.view.swing.combobox.ChangeableJComboBox;
import net.sf.anathema.character.equipment.creation.view.swing.combobox.ConfigurableListCellRenderer;
import net.sf.anathema.character.equipment.creation.view.swing.combobox.IChangeableJComboBox;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

import javax.swing.JComponent;

public class AdditiveObjectSelectionView<T> implements ObjectSelectionView<T> {

  private final IChangeableJComboBox<T> typeBox = new ChangeableJComboBox<>();

  @Override
  public void setEnabled(boolean enabled) {
    throw new NotYetImplementedException();
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<T> listener) {
    typeBox.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void removeObjectSelectionChangedListener(ObjectValueListener<T> listener) {
    typeBox.removeObjectSelectionChangeListener(listener);
  }

  @Override
  public void setObjects(T[] objects) {
    typeBox.setObjects(objects);
  }

  @Override
  public void setSelectedObject(T object) {
    typeBox.setSelectedObject(object);
  }

  @Override
  public T getSelectedObject() {
    return typeBox.getSelectedObject();
  }

  @Override
  public boolean isObjectSelected() {
    return getSelectedObject() != null;
  }

  public JComponent getComponent() {
    return typeBox.getComponent();
  }

  public void setRenderer(AgnosticUIConfiguration<T> agnosticUIConfiguration) {
    typeBox.setRenderer(new ConfigurableListCellRenderer(agnosticUIConfiguration));
  }
}