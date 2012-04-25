package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.charmentry.presenter.model.ISimpleSpecialsEntryModel;

public class SimpleSpecialsEntryModel implements ISimpleSpecialsEntryModel {
  private int speed = DEFAULT_SPEED;
  private int defenseModifier = DEFAULT_DEFENSE_MODIFIER;
  private TurnType turnType = TurnType.Tick;

  @Override
  public TurnType[] getTurnTypes() {
    return TurnType.values();
  }

  @Override
  public void setTurnType(TurnType newValue) {
    this.turnType = newValue;
  }

  @Override
  public void reset() {
    this.speed = DEFAULT_SPEED;
    this.defenseModifier = DEFAULT_DEFENSE_MODIFIER;
  }

  @Override
  public void setSpeed(int newValue) {
    this.speed = newValue;
  }

  @Override
  public void setDefenseValue(int newValue) {
    this.defenseModifier = newValue;
  }

  @Override
  public TurnType getTurnType() {
    return turnType;
  }

  @Override
  public int getDefenseModifier() {
    return defenseModifier;
  }

  @Override
  public int getSpeed() {
    return speed;
  }
}