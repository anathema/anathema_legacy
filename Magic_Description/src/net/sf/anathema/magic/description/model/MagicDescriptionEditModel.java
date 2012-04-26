package net.sf.anathema.magic.description.model;

import net.sf.anathema.lib.control.IChangeListener;

public interface MagicDescriptionEditModel {

  boolean isActive();

  void setEditId(String magicId);
  
  String getEditId();

  String getCurrentDescription();

  void updateCurrentDescription(String newDescription);

  void addDescriptionChangedListener(IChangeListener listener);

  void removeDescriptionChangeListener(IChangeListener listener);
}
