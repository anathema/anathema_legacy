package net.sf.anathema.magic.description.model;

import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class ProxyMagicDescriptionEditModel implements MagicDescriptionEditModel {

  private final Announcer<IChangeListener> changeControl = Announcer.to(IChangeListener.class);
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
    changeControl.addListener(listener);
  }

  @Override
  public void removeDescriptionChangeListener(IChangeListener listener) {
    changeControl.removeListener(listener);
  }
}
