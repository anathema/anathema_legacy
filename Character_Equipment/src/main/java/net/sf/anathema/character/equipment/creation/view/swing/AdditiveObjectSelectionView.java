package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;

import javax.swing.JPanel;

public class AdditiveObjectSelectionView<T> implements AdditiveView, ObjectSelectionView<T> {

  private final IChangeableJComboBox<T> typeBox = new ChangeableJComboBox<>();

  @Override
  public void addTo(JPanel panel) {
    panel.add(typeBox.getComponent(), new CC().growX().spanX());
  }

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
}