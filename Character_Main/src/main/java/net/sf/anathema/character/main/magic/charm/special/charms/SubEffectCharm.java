package net.sf.anathema.character.main.magic.charm.special.charms;

public class SubEffectCharm extends MultipleEffectCharm implements ISubEffectCharm {

  private final double pointCost;

  public SubEffectCharm(String charmId, String[] subEffectIds, double pointCost) {
    super(charmId, subEffectIds);
    this.pointCost = pointCost;
  }

  @Override
  public double getPointCost() {
    return pointCost;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitSubEffectCharm(this);
  }
}