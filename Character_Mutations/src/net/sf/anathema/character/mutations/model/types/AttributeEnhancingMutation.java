package net.sf.anathema.character.mutations.model.types;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.mutations.model.IMutationVisitor;
import net.sf.anathema.character.mutations.model.Mutation;

public class AttributeEnhancingMutation extends Mutation {

  private final AttributeType type;
  private final int bonus;

  public AttributeEnhancingMutation(String id, AttributeType type, int bonus) {
    super(id);
    this.type = type;
    this.bonus = bonus;
  }

  @Override
  public void accept(IMutationVisitor visitor) {
    visitor.visitAttributeEnhancingMutation(this);
  }

  public AttributeType getAttributeType() {
    return type;
  }

  public int getBonus() {
    return bonus;
  }
}