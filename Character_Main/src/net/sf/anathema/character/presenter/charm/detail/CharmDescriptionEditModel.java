package net.sf.anathema.character.presenter.charm.detail;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface CharmDescriptionEditModel {

  boolean isActive();

  void setEditId(String charmId);
  
  String getEditId();

  String getCurrentDescription();

  void updateCurrentDescription(String newDescription);

  void addDescriptionChangedListener(IChangeListener listener);

  void removeDescriptionChangeListener(IChangeListener listener);
}
