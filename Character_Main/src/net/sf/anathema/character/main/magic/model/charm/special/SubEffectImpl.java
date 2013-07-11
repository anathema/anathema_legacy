package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.data.Condition;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.jmock.example.announcer.Announcer;

public class SubEffectImpl extends SimpleIdentifier implements SubEffect2 {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private boolean learned = false;
  private boolean creationLearned = false;
  private final Condition learnable;
  private ExperienceModel experience;

  public SubEffectImpl(String subeffectId, ExperienceModel experience, Condition learnable) {
    super(subeffectId);
    this.experience = experience;
    this.learnable = learnable;
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
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