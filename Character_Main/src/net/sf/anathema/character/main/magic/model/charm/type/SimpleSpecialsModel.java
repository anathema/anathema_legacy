package net.sf.anathema.character.main.magic.model.charm.type;

public class SimpleSpecialsModel implements ISimpleSpecialsModel {

  private final int speed;
  private final TurnType type;
  private final int defense;

  public SimpleSpecialsModel(int speed, TurnType type, int defense) {
    this.speed = speed;
    this.type = type;
    this.defense = defense;
  }

  @Override
  public int getDefenseModifier() {
    return defense;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public TurnType getTurnType() {
    return type;
  }
}