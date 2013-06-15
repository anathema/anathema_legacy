package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.impl.model.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.lib.util.Identified;

public class ExperienceModelImpl implements ExperienceModel {
  private final IExperiencePointConfiguration experiencePoints = new ExperiencePointConfiguration();
  private final CharacterModelContext context;
  private boolean experienced = false;

  public ExperienceModelImpl(CharacterModelContext context) {
    this.context = context;
    initExperienceListening();
  }

  private void initExperienceListening() {
    experiencePoints.addExperiencePointConfigurationListener(new IExperiencePointConfigurationListener() {

      @Override
      public void entryAdded(IExperiencePointEntry entry) {
        context.getCharacterListening().fireCharacterChanged();
      }

      @Override
      public void entryChanged(IExperiencePointEntry entry) {
        context.getCharacterListening().fireCharacterChanged();
      }

      @Override
      public void entryRemoved(IExperiencePointEntry entry) {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
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
    context.setExperienced(experienced);
    context.getCharacterListening().fireExperiencedChanged(experienced);
  }

  @Override
  public Identified getId() {
    return ID;
  }

  @Override
  public void initListening(ChangeAnnouncer announcer) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
