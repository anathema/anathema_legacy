package net.sf.anathema.lib.workflow.container.model;

import java.util.Arrays;

import net.sf.anathema.lib.container.DefaultSelectionContainer;
import net.sf.anathema.lib.container.IGenericSelectionContainer;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.container.ISelectionContainerModel;

public class SelectionContainerModel<V> implements ISelectionContainerModel<V> {

  public static <V> SelectionContainerModel<V> createDefault(Class<V> componentType, V[] availableValues) {
    return new SelectionContainerModel<V>(new DefaultSelectionContainer<V>(componentType, availableValues));
  }

  private final ChangeControl changeControl = new ChangeControl();
  private final IGenericSelectionContainer<V> container;

  public SelectionContainerModel(IGenericSelectionContainer<V> container) {
    this.container = container;
  }

  @Override
  public void setSelectedValues(V[] values) {
    if (Arrays.equals(container.getValues(), values)) {
      return;
    }
    container.setValues(values);
    changeControl.fireChangedEvent();
  }

  @Override
  public V[] getSelectedValues() {
    return container.getValues();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }

  @Override
  public V[] getAllAvailableValues() {
    return container.getAllAvailableValues();
  }
}