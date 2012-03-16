package net.sf.anathema.charm.description.model;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class ProxyCharmDescriptionEditModel implements CharmDescriptionEditModel {

  private final ChangeControl changeControl = new ChangeControl();
  private final IChangeListener notifyingListener = new NotifyingChangeListener(changeControl);
  private CharmDescriptionEditModel delegate = new NullCharmDescriptionEditModel();

  @Override
  public boolean isActive() {
    return delegate.isActive();
  }

  @Override
  public void setEditId(String charmId) {
    delegate.setEditId(charmId);
  }
  
  @Override
  public String getEditId() {
    return delegate.getEditId();
  }

  @Override
  public String getCurrentDescription() {
    return delegate.getCurrentDescription();
  }

  @Override
  public void updateCurrentDescription(String newDescription) {
    delegate.updateCurrentDescription(newDescription);
  }

  public void setDelegate(CharmDescriptionEditModel delegate) {
    this.delegate.removeDescriptionChangeListener(notifyingListener);
    this.delegate = delegate;
    this.delegate.addDescriptionChangedListener(notifyingListener);
  }

  @Override
  public void addDescriptionChangedListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeDescriptionChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }
}
