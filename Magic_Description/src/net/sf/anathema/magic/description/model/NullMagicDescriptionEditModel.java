package net.sf.anathema.magic.description.model;

import net.sf.anathema.lib.control.change.IChangeListener;

public class NullMagicDescriptionEditModel implements MagicDescriptionEditModel {
  @Override
  public boolean isActive() {
    return false;
  }

  @Override
  public void setEditId(String magicId) {
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
