package net.sf.anathema.charmentry.model;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class ReflexiveSpecialsEntryModel implements IReflexiveSpecialsEntryModel {
  private static final int DEFAULT_STEP = 1;
  private static final int DEFAULT_DEFENSE_STEP = 2;

  private final IReflexiveSpecialsArbitrator arbitrator;
  private final ChangeControl control = new ChangeControl();
  private int step = DEFAULT_STEP;
  private int defenseStep = DEFAULT_DEFENSE_STEP;
  private boolean split = false;

  public ReflexiveSpecialsEntryModel(IReflexiveSpecialsArbitrator arbitrator) {
    this.arbitrator = arbitrator;
    arbitrator.addSpecialsChangeListener(new IChangeListener() {
      public void changeOccured() {
        control.fireChangedEvent();
      }
    });
  }

  public void addChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public Integer getSecondaryStep() {
    return defenseStep;
  }

  public int getPrimaryStep() {
    return step;
  }

  public boolean isSplitEnabled() {
    return split;
  }

  public void setSplitEnabled(boolean splitEnabled) {
    if (arbitrator.isReflexiveSpecialsAvailable()) {
      if (splitEnabled == split) {
        return;
      }
      this.split = splitEnabled;
      control.fireChangedEvent();
    }
  }

  public void reset() {
    this.step = DEFAULT_STEP;
    this.defenseStep = DEFAULT_DEFENSE_STEP;
    this.split = false;
    control.fireChangedEvent();
  }

  public void setDefenseStep(int newValue) {
    if (arbitrator.isReflexiveSpecialsAvailable() && split) {
      this.defenseStep = newValue;
      control.fireChangedEvent();
    }
  }

  public void setStep(int newValue) {
    if (arbitrator.isReflexiveSpecialsAvailable()) {
      this.step = newValue;
      control.fireChangedEvent();
    }
  }
}