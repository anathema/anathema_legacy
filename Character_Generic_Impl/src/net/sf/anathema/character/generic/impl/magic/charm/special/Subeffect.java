package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.data.ICondition;
import net.sf.anathema.lib.util.Identifier;
import org.jmock.example.announcer.Announcer;

public class Subeffect extends Identifier implements ISubeffect {

  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final IBasicCharacterData data;
  private boolean learned = false;
  private boolean creationLearned = false;
  private final ICondition learnable;

  public Subeffect(String subeffectId, IBasicCharacterData data, ICondition learnable) {
    super(subeffectId);
    this.data = data;
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
    if (!data.isExperienced()) {
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
    if (!data.isExperienced()) {
      setCreationLearned(learned);
    }
    else {
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