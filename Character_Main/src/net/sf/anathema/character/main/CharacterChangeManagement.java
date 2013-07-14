package net.sf.anathema.character.main;

import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

public class CharacterChangeManagement implements ChangeManagement {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
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
  public void addDirtyListener(ChangeListener changeListener) {
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
  public void removeDirtyListener(ChangeListener changeListener) {
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