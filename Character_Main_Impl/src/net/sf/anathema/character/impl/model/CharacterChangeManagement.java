package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.framework.repository.IChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class CharacterChangeManagement implements IChangeManagement {

  private final ChangeControl control = new ChangeControl();
  private boolean dirty = false;
  private final ICharacterChangeListener listener = new ICharacterChangeListener() {
    public void casteChanged() {
      setDirty();
    }

    public void characterChanged() {
      setDirty();
    }

    public void experiencedChanged(boolean experienced) {
      setDirty();
    }

    public void traitChanged(ITraitType type) {
      setDirty();
    }
  };
  private final IObjectValueChangedListener<String> textListener = new IObjectValueChangedListener<String>() {
    public void valueChanged(String newValue) {
      setDirty();
    }
  };

  public ICharacterChangeListener getStatisticsChangeListener() {
    return listener;
  }

  public IObjectValueChangedListener<String> getDescriptionChangeListener() {
    return textListener;
  }

  public boolean isDirty() {
    return dirty;
  }

  public void addDirtyListener(IChangeListener changeListener) {
    control.addChangeListener(changeListener);
  }

  private void setDirty() {
    this.dirty = true;
    control.fireChangedEvent();
  }

  public void setClean() {
    this.dirty = false;
    control.fireChangedEvent();
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    control.removeChangeListener(changeListener);
  }
}