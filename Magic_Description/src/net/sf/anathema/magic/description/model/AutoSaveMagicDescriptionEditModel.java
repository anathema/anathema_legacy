package net.sf.anathema.magic.description.model;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;

public class AutoSaveMagicDescriptionEditModel implements MagicDescriptionEditModel {

  private final ChangeControl changeControl = new ChangeControl();
  private final MagicDescriptionDataBase dataBase;
  private String editId = "";
  private String currentDescription;

  public AutoSaveMagicDescriptionEditModel(MagicDescriptionDataBase dataBase) {
    this.dataBase = dataBase;
  }

  @Override
  public boolean isActive() {
    return true;
  }

  @Override
  public void setEditId(String magicId) {
    this.editId = magicId;
    setCurrentDescriptionInternal(dataBase.loadDescription(magicId));
  }

  @Override
  public String getCurrentDescription() {
    return currentDescription;
  }
  
  public String getEditId() {
    return editId;
  }

  @Override
  public void updateCurrentDescription(String newDescription) {
    if (ObjectUtilities.equals(currentDescription, newDescription)) {
      return;
    }
    dataBase.saveDescription(editId, newDescription);
    setCurrentDescriptionInternal(newDescription);
  }

  private void setCurrentDescriptionInternal(String newDescription) {
    if (ObjectUtilities.equals(currentDescription, newDescription)) {
      return;
    }
    this.currentDescription = newDescription;
    changeControl.fireChangedEvent();
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
