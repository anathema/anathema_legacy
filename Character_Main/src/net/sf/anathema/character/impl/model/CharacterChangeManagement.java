package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.framework.repository.IChangeManagement;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import org.jmock.example.announcer.Announcer;

public class CharacterChangeManagement implements IChangeManagement {

  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private boolean dirty = false;
  private final ICharacterChangeListener listener = new ICharacterChangeListener() {
    @Override
    public void casteChanged() {
      setDirty();
    }

    @Override
    public void characterChanged() {
      setDirty();
    }

    @Override
    public void experiencedChanged(boolean experienced) {
      setDirty();
    }

    @Override
    public void traitChanged(ITraitType type) {
      setDirty();
    }
  };
  private final ObjectValueListener<String> textListener = new ObjectValueListener<String>() {
    @Override
    public void valueChanged(String newValue) {
      setDirty();
    }
  };

  public ICharacterChangeListener getStatisticsChangeListener() {
    return listener;
  }

  public ObjectValueListener<String> getDescriptionChangeListener() {
    return textListener;
  }

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    control.addListener(changeListener);
  }

  private void setDirty() {
    this.dirty = true;
    control.announce().changeOccurred();
  }

  @Override
  public void setClean() {
    this.dirty = false;
    control.announce().changeOccurred();
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    control.removeListener(changeListener);
  }
}