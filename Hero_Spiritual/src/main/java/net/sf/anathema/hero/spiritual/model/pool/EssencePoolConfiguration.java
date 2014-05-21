package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.spiritual.template.EssencePoolTemplate;
import net.sf.anathema.hero.spiritual.template.PoolPartTemplate;
import net.sf.anathema.hero.traits.model.TraitMap;

import java.util.ArrayList;
import java.util.List;

public class EssencePoolConfiguration {

  private EssencePoolTemplate template;

  public EssencePoolConfiguration(EssencePoolTemplate template) {
    this.template = template;
  }

  public FactorizedTrait[] getPersonalTraits(TraitMap traitMap) {
    return createFactorizedTraits(template.personalPool, traitMap);
  }

  public FactorizedTrait[] getPeripheralTraits(TraitMap traitMap) {
    return createFactorizedTraits(template.peripheralPool, traitMap);
  }

  public boolean isEssenceUser() {
    return template.isEssenceUser;
  }

  private FactorizedTrait[] createFactorizedTraits(List<PoolPartTemplate> parts, TraitMap traitMap) {
    if (parts.isEmpty()) {
      return new FactorizedTrait[0];
    }
    List<FactorizedTrait> traits = new ArrayList<>();
    for (PoolPartTemplate part : parts) {
      ValuedTraitType trait = traitMap.getTrait(part.traitType);
      traits.add(new FactorizedTrait(trait, part.multiplier));
    }
    return traits.toArray(new FactorizedTrait[traits.size()]);
  }
}