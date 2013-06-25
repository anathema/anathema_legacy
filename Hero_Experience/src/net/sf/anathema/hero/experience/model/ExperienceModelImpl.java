package net.sf.anathema.hero.experience.model;

import net.sf.anathema.character.impl.model.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.hero.change.AnnounceChangeListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.jmock.example.announcer.Announcer;

public class ExperienceModelImpl implements ExperienceModel, HeroModel {
  private final IExperiencePointConfiguration experiencePoints = new ExperiencePointConfiguration();
  private final Announcer<ChangeListener> stateAnnouncer = new Announcer<>(ChangeListener.class);
  private boolean experienced = false;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    stateAnnouncer.addListener(new AnnounceChangeListener(announcer, ExperienceChange.FLAVOR_EXPERIENCE_STATE));
    experiencePoints.addExperiencePointConfigurationListener(new AnnounceExperiencePointChange(announcer));
  }

  // todo (sandra): redirect to ChangeAnnouncer
  public void addStateChangeListener(ChangeListener listener) {
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
}
