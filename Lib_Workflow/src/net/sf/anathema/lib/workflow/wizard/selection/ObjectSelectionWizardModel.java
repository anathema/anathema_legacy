package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.control.change.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class ObjectSelectionWizardModel<V> implements IObjectSelectionWizardModel<V> {

  private V value;
  private final V[] allObjects;
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final ILegalityProvider<V> provider;

  public ObjectSelectionWizardModel(V[] allObjects, ILegalityProvider<V> provider) {
    this.allObjects = allObjects;
    this.provider = provider;
  }

  @Override
  public V getSelectedObject() {
    return value;
  }

  @Override
  public void setSelectedObject(V value) {
    if (value == this.value) {
      return;
    }
    if (provider.isLegal(value)) {
      this.value = value;
    }
    control.announce().changeOccurred();
  }

  @Override
  public V[] getRegisteredObjects() {
    return allObjects;
  }

  @Override
  public void addListener(IChangeListener inputListener) {
    control.addListener(inputListener);
  }
}