package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.charmentry.presenter.model.ISimpleSpecialsEntryModel;

public class SimpleSpecialsEntryModel implements ISimpleSpecialsEntryModel {
  private int speed = DEFAULT_SPEED;
  private int defenseModifier = DEFAULT_DEFENSE_MODIFIER;
  private TurnType turnType = TurnType.Tick;

  public TurnType[] getTurnTypes() {
    return TurnType.values();
  }

  public void setTurnType(TurnType newValue) {
    this.turnType = newValue;
  }

  public void reset() {
    this.speed = DEFAULT_SPEED;
    this.defenseModifier = DEFAULT_DEFENSE_MODIFIER;
  }

  public void setSpeed(int newValue) {
    this.speed = newValue;
  }

  public void setDefenseValue(int newValue) {
    this.defenseModifier = newValue;
  }

  public TurnType getTurnType() {
    return turnType;
  }

  public int getDefenseModifier() {
    return defenseModifier;
  }

  public int getSpeed() {
    return speed;
  }
}