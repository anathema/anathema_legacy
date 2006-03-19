package net.sf.anathema.character.lunar.beastform.model.gift;

public class AttributePointsProvidingGift extends Gift {

  private final int points;

  public AttributePointsProvidingGift(String id, int points) {
    super(id);
    this.points = points;
  }

  @Override
  public void accept(IGiftVisitor visitor) {
    visitor.visitAttributePointsProvidingGift(this);
  }

  public int getProvidedPoints() {
    return points;
  }
}