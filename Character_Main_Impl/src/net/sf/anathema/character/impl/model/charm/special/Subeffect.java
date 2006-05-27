package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.model.charm.special.ISubeffect;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.Identificate;

public class Subeffect extends Identificate implements ISubeffect {

  private final ChangeControl control = new ChangeControl();
  private final IBasicCharacterData data;
  private boolean learned = false;
  private boolean creationLearned = false;

  public Subeffect(String subeffectId, IBasicCharacterData data) {
    super(subeffectId);
    this.data = data;
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
    if (!data.isExperienced()) {
      this.learned = learned;
      this.creationLearned = learned;
      control.fireChangedEvent();
      return;
    }
    if (!creationLearned) {
      this.learned = learned;
      control.fireChangedEvent();
    }
  }
}