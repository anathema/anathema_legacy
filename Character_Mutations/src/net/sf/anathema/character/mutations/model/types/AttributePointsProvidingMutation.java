package net.sf.anathema.character.mutations.model.types;

import net.sf.anathema.character.mutations.model.IMutationVisitor;
import net.sf.anathema.character.mutations.model.Mutation;

public class AttributePointsProvidingMutation extends Mutation {

  private final int points;

  public AttributePointsProvidingMutation(String id, int points) {
    super(id);
    this.points = points;
  }

  @Override
  public void accept(IMutationVisitor visitor) {
    visitor.visitAttributePointsProvidingMutation(this);
  }

  public int getProvidedPoints() {
    return points;
  }
}