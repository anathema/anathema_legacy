package net.sf.anathema.campaign.music.model;

import net.sf.anathema.campaign.music.presenter.ISelectionContainerModel;
import net.sf.anathema.lib.container.DefaultSelectionContainer;
import net.sf.anathema.lib.container.IGenericSelectionContainer;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.Arrays;

public class SelectionContainerModel<V> implements ISelectionContainerModel<V> {

  public static <V> SelectionContainerModel<V> createDefault(Class<V> componentType, V[] availableValues) {
    return new SelectionContainerModel<>(new DefaultSelectionContainer<>(componentType, availableValues));
  }

  private final Announcer<IChangeListener> changeControl = Announcer.to(IChangeListener.class);
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
    changeControl.announce().changeOccurred();
  }

  @Override
  public V[] getSelectedValues() {
    return container.getValues();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeListener(listener);
  }

  @Override
  public V[] getAllAvailableValues() {
    return container.getAllAvailableValues();
  }
}