package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.impl.model.charm.CharmSpecialist;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.data.Condition;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.jmock.example.announcer.Announcer;

public class Subeffect extends SimpleIdentifier implements ISubeffect {

  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private boolean learned = false;
  private boolean creationLearned = false;
  private final Condition learnable;
  private ExperienceModel experience;

  public Subeffect(String subeffectId, ExperienceModel experience, Condition learnable) {
    super(subeffectId);
    this.experience = experience;
    this.learnable = learnable;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public boolean isCreationLearned() {
    return creationLearned;
  }

  @Override
  public boolean isLearned() {
    if (!experience.isExperienced()) {
      return creationLearned;
    }
    return learned;
  }

  @Override
  public void setLearned(boolean learned) {
    if (this.learned == learned) {
      return;
    }
    if (learned && !learnable.isFulfilled()) {
      return;
    }
    if (!experience.isExperienced()) {
      setCreationLearned(learned);
    } else {
      setExperienceLearned(learned);
    }
  }

  @Override
  public void setCreationLearned(boolean creationLearned) {
    this.creationLearned = creationLearned;
    this.learned = creationLearned;
    control.announce().changeOccurred();
  }

  @Override
  public void setExperienceLearned(boolean experienceLearned) {
    if (!creationLearned) {
      this.learned = experienceLearned;
      control.announce().changeOccurred();
    }
  }
}