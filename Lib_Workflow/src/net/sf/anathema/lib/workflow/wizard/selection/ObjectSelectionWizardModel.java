package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class ObjectSelectionWizardModel<V> implements IObjectSelectionWizardModel<V> {

  private V value;
  private final V[] allObjects;
  private final ChangeControl control = new ChangeControl();
  private final ILegalityProvider<V> provider;

  public ObjectSelectionWizardModel(V[] allObjects, ILegalityProvider<V> provider) {
    this.allObjects = allObjects;
    this.provider = provider;
  }

  public V getSelectedObject() {
    return value;
  }

  public void setSelectedObject(V value) {
    if (value == this.value) {
      return;
    }
    if (provider.isLegal(value)) {
      this.value = value;
    }
    control.fireChangedEvent();
  }

  public V[] getRegisteredObjects() {
    return allObjects;
  }

  public void addListener(IChangeListener inputListener) {
    control.addChangeListener(inputListener);
  }
}