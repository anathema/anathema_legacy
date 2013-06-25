package net.sf.anathema.magic.description.model;

import com.google.common.base.Objects;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import org.jmock.example.announcer.Announcer;

public class AutoSaveMagicDescriptionEditModel implements MagicDescriptionEditModel {

  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);
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
  
  @Override
  public String getEditId() {
    return editId;
  }

  @Override
  public void updateCurrentDescription(String newDescription) {
    if (Objects.equal(currentDescription, newDescription)) {
      return;
    }
    dataBase.saveDescription(editId, newDescription);
    setCurrentDescriptionInternal(newDescription);
  }

  private void setCurrentDescriptionInternal(String newDescription) {
    if (Objects.equal(currentDescription, newDescription)) {
      return;
    }
    this.currentDescription = newDescription;
    changeControl.announce().changeOccurred();
  }

  @Override
  public void addDescriptionChangedListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public void removeDescriptionChangeListener(ChangeListener listener) {
    changeControl.removeListener(listener);
  }
}
