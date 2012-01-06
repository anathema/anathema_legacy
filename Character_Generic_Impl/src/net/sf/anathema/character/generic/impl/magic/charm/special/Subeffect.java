package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.util.Identificate;

public class Subeffect extends Identificate implements ISubeffect {

  private final ChangeControl control = new ChangeControl();
  private final IBasicCharacterData data;
  private boolean learned = false;
  private boolean creationLearned = false;
  private final ICondition learnable;

  public Subeffect(String subeffectId, IBasicCharacterData data, ICondition learnable) {
    super(subeffectId);
    this.data = data;
    this.learnable = learnable;
  }

  public void addChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public boolean isCreationLearned() {
    return creationLearned;
  }

  public boolean isLearned() {
    if (!data.isExperienced()) {
      return creationLearned;
    }
    return learned;
  }

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

  public void setCreationLearned(boolean creationLearned) {
    this.creationLearned = creationLearned;
    this.learned = creationLearned;
    control.fireChangedEvent();
  }

  public void setExperienceLearned(boolean experienceLearned) {
    if (!creationLearned) {
      this.learned = experienceLearned;
      control.fireChangedEvent();
    }
  }
}