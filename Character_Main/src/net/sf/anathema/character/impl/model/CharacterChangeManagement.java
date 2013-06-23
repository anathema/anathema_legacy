package net.sf.anathema.character.impl.model;

import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class CharacterChangeManagement implements ChangeManagement {

  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private boolean dirty = false;
  private Hero hero;

  public CharacterChangeManagement(Hero hero) {
    this.hero = hero;
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

  public void initListening() {
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        setDirty();
      }
    });
  }
}