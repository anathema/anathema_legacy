package net.sf.anathema.character.main.magic.model.charm.special;

public class SubEffectCharm2 extends MultipleEffectCharm implements ISubEffectCharm2 {

  private final double pointCost;

  public SubEffectCharm2(String charmId, String[] subEffectIds, double pointCost) {
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