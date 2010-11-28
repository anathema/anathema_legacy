package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.generic.traits.types.AttributeType;

public class AttributeEnhancingGift extends Gift {

  private final AttributeType type;
  private final int bonus;

  public AttributeEnhancingGift(String id, AttributeType type, int bonus) {
    super(id);
    this.type = type;
    this.bonus = bonus;
  }

  @Override
  public void accept(IGiftVisitor visitor) {
    visitor.visitAttributeEnhancingGift(this);
  }

  public AttributeType getAttributeType() {
    return type;
  }

  public int getBonus() {
    return bonus;
  }
}