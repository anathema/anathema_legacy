package net.sf.anathema.magic.description.model;

import net.sf.anathema.lib.control.ChangeListener;

public interface MagicDescriptionEditModel {

  boolean isActive();

  void setEditId(String magicId);
  
  String getEditId();

  String getCurrentDescription();

  void updateCurrentDescription(String newDescription);

  void addDescriptionChangedListener(ChangeListener listener);

  void removeDescriptionChangeListener(ChangeListener listener);
}
