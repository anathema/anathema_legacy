package net.sf.anathema.magic.description.model;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class ProxyMagicDescriptionEditModel implements MagicDescriptionEditModel {

  private final ChangeControl changeControl = new ChangeControl();
  private final IChangeListener notifyingListener = new NotifyingChangeListener(changeControl);
  private MagicDescriptionEditModel delegate = new NullMagicDescriptionEditModel();

  @Override
  public boolean isActive() {
    return delegate.isActive();
  }

  @Override
  public void setEditId(String magicId) {
    delegate.setEditId(magicId);
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

  public void setDelegate(MagicDescriptionEditModel delegate) {
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
