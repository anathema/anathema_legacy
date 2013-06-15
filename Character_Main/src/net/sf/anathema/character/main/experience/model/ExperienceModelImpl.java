package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.change.AnnounceChangeListener;
import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.impl.model.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;
import org.jmock.example.announcer.Announcer;

public class ExperienceModelImpl implements ExperienceModel {
  private final IExperiencePointConfiguration experiencePoints = new ExperiencePointConfiguration();
  private final Announcer<IChangeListener> stateAnnouncer = new Announcer<>(IChangeListener.class);
  private boolean experienced = false;

  public void addStateChangeListener(IChangeListener listener) {
    stateAnnouncer.addListener(listener);
  }

  @Override
  public IExperiencePointConfiguration getExperiencePoints() {
    return experiencePoints;
  }

  @Override
  public boolean isExperienced() {
    return experienced;
  }

  @Override
  public void setExperienced(boolean experienced) {
    if (this.experienced) {
      return;
    }
    this.experienced = experienced;
    stateAnnouncer.announce().changeOccurred();
  }

  @Override
  public Identified getId() {
    return ID;
  }

  @Override
  public void initListening(ChangeAnnouncer announcer) {
    stateAnnouncer.addListener(new AnnounceChangeListener(announcer, ExperienceChange.FLAVOR_EXPERIENCE_STATE));
    experiencePoints.addExperiencePointConfigurationListener(new AnnounceExperiencePointChange(announcer));
  }
}
