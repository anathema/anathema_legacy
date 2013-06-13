package net.sf.anathema.character.generic.framework.xml.essence;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EssencePoolConfiguration implements IEssencePoolConfiguration {

  private final int essenceMultiplier;
  private final int willpowerMultiplier;
  private final List<IVirtuePoolPart> virtuePoolParts = new ArrayList<>();

  public EssencePoolConfiguration(int essenceMultiplier, int willpowerMultiplier) {
    this.essenceMultiplier = essenceMultiplier;
    this.willpowerMultiplier = willpowerMultiplier;
  }

  @Override
  public int getEssenceMultiplier() {
    return essenceMultiplier;
  }

  @Override
  public int getWillpowerMultiplier() {
    return willpowerMultiplier;
  }

  @Override
  public FactorizedTrait[] createVirtueFactorizedTrait(IGenericTrait[] virtues) {
    List<FactorizedTrait> traits = new ArrayList<>();
    for (IVirtuePoolPart part : virtuePoolParts) {
      Collections.addAll(traits, part.createFactorizedTrait(virtues));
    }
    return traits.toArray(new FactorizedTrait[traits.size()]);
  }

  public void addVirtuePoolPart(IVirtuePoolPart virtuePoolPart) {
    virtuePoolParts.add(virtuePoolPart);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return virtuePoolParts.hashCode() + 3 * essenceMultiplier + 11 * willpowerMultiplier;
  }
}