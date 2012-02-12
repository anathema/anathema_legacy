package net.sf.anathema.character.generic.framework.xml.essence;

import java.util.List;

import net.sf.anathema.character.generic.impl.traits.ValueWeightGenericTraitSorter;
import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class RankVirtuePoolPart implements IVirtuePoolPart {

  private final int ranks;
  private final int virtueMultiplier;

  public RankVirtuePoolPart(int ranks, int virtueMultiplier) {
    this.ranks = ranks;
    this.virtueMultiplier = virtueMultiplier;
  }

  public FactorizedTrait createFactorizedTrait(IGenericTrait[] virtues) {
    List<IGenericTrait> sortedVirtues = new ValueWeightGenericTraitSorter().sortDescending(virtues);
    IGenericTrait[] topVirtues = new IGenericTrait[ranks];
    for (int index = 0; index < ranks; index++) {
      topVirtues[index] = sortedVirtues.get(index);
    }
    return new FactorizedTrait(topVirtues, virtueMultiplier);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return ranks + 7 * virtueMultiplier;
  }
}