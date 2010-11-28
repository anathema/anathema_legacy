package net.sf.anathema.character.generic.impl.magic.charm.type;

import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;

public class SimpleSpecialsModel implements ISimpleSpecialsModel {

  private final int speed;
  private final TurnType type;
  private final int defense;

  public SimpleSpecialsModel(int speed, TurnType type, int defense) {
    this.speed = speed;
    this.type = type;
    this.defense = defense;
  }

  public int getDefenseModifier() {
    return defense;
  }

  public int getSpeed() {
    return speed;
  }

  public TurnType getTurnType() {
    return type;
  }
}