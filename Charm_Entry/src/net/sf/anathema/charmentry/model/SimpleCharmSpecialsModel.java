package net.sf.anathema.charmentry.model;

import net.sf.anathema.charmentry.presenter.ISimpleCharmSpecialsModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SimpleCharmSpecialsModel implements ISimpleCharmSpecialsModel {
  private static final int DEFAULT_SPEED = 6;
  private static final int DEFAULT_DEFENSE_MODIFIER = -1;

  private int speed = DEFAULT_SPEED;
  private int defenseModifier = DEFAULT_DEFENSE_MODIFIER;
  private final ISimpleSpecialsArbitrator arbitrator;
  private final ChangeControl control = new ChangeControl();

  public SimpleCharmSpecialsModel(ISimpleSpecialsArbitrator arbitrator) {
    this.arbitrator = arbitrator;
    arbitrator.addSpecialsChangeListener(new IChangeListener() {
      public void changeOccured() {
        control.fireChangedEvent();
      }
    });
  }

  public void reset() {
    this.speed = DEFAULT_SPEED;
    this.defenseModifier = DEFAULT_DEFENSE_MODIFIER;
    control.fireChangedEvent();
  }

  public void setSpeed(int newValue) {
    if (arbitrator.isSimpleSpecialsAvailable()) {
      this.speed = newValue;
      control.fireChangedEvent();
    }
  }

  public void setDefenseValue(int newValue) {
    if (arbitrator.isSimpleSpecialsAvailable()) {
      this.defenseModifier = newValue;
      control.fireChangedEvent();
    }
  }

  public int getDefenseValue() {
    return defenseModifier;
  }

  public int getSpeed() {
    return speed;
  }

  public void addChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }
}