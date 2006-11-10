package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;

public class SubeffectCharm extends MultipleEffectCharm implements ISubeffectCharm {

  private final double pointCost;

  public SubeffectCharm(String charmId, String[] subeffectIds, double pointCost) {
    super(charmId, subeffectIds);
    this.pointCost = pointCost;
  }

  public double getPointCost() {
    return pointCost;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitSubeffectCharm(this);
  }
}