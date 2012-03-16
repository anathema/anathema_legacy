package net.sf.anathema.character.presenter.charm.detail;

import net.sf.anathema.lib.control.change.IChangeListener;

public class NullCharmDescriptionEditModel implements CharmDescriptionEditModel {
  @Override
  public boolean isActive() {
    return false;
  }

  @Override
  public void setEditId(String charmId) {
    //nothing to do
  }


  @Override
  public String getEditId() {
    return null;
  }

  @Override
  public String getCurrentDescription() {
    return null;
  }

  @Override
  public void updateCurrentDescription(String newDescription) {
    //nothing to do
  }

  @Override
  public void addDescriptionChangedListener(IChangeListener listener) {
    //nothing to do
  }

  @Override
  public void removeDescriptionChangeListener(IChangeListener listener) {
    //nothing to do
  }
}
