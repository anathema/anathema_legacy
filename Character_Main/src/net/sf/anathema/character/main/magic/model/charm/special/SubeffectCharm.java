package net.sf.anathema.character.main.magic.model.charm.special;

public class SubeffectCharm extends MultipleEffectCharm implements ISubeffectCharm {

  private final double pointCost;

  public SubeffectCharm(String charmId, String[] subeffectIds, double pointCost) {
    super(charmId, subeffectIds);
    this.pointCost = pointCost;
  }

  @Override
  public double getPointCost() {
    return pointCost;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitSubeffectCharm(this);
  }
}