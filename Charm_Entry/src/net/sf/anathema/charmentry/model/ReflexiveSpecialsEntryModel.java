package net.sf.anathema.charmentry.model;

import net.sf.anathema.charmentry.presenter.model.IReflexiveSpecialsEntryModel;

public class ReflexiveSpecialsEntryModel implements IReflexiveSpecialsEntryModel {
  private static final int DEFAULT_STEP = 1;
  private static final int DEFAULT_DEFENSE_STEP = 2;

  private Integer step = DEFAULT_STEP;
  private Integer defenseStep = DEFAULT_DEFENSE_STEP;
  private boolean split = false;

  public Integer getSecondaryStep() {
    return defenseStep;
  }

  public Integer getPrimaryStep() {
    return step;
  }

  public boolean isSplitEnabled() {
    return split;
  }

  public void setSplitEnabled(boolean splitEnabled) {
    this.split = splitEnabled;
  }

  public void setDefenseStep(Integer newValue) {
    if (split) {
      this.defenseStep = newValue;
    }
  }

  public void setStep(Integer newValue) {
    this.step = newValue;
  }
}