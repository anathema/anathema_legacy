package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.character.main.template.essence.FactorizedTrait;
import net.sf.anathema.character.main.template.essence.IEssenceTemplate;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.xml.essence.IEssencePoolConfiguration;
import net.sf.anathema.hero.spiritual.template.EssencePoolTemplate;
import net.sf.anathema.hero.spiritual.template.PoolPartTemplate;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EssencePoolConfiguration {

  private boolean isEssenceUser = false;
  private IEssencePoolConfiguration personalPoolConfiguration;
  private IEssencePoolConfiguration peripheralPoolConfiguration;
  private EssencePoolTemplate template;

  public EssencePoolConfiguration(EssencePoolTemplate template) {
    this.template = template;
  }

  private FactorizedTrait[] createFactorizedTraits(List<PoolPartTemplate> parts, ValuedTraitType willpower, ValuedTraitType[] virtues,
                                                   ValuedTraitType essence) {
    if (parts.isEmpty()) {
      return new FactorizedTrait[0];
    }
    List<FactorizedTrait> traits = new ArrayList<>();
    for(PoolPartTemplate part : parts) {
      ValuedTraitType trait = part.traitType == OtherTraitType.Essence ? essence : willpower;
      traits.add(new FactorizedTrait(trait, part.multiplier));
    }
    return traits.toArray(new FactorizedTrait[traits.size()]);
  }

  public FactorizedTrait[] getPersonalTraits(ValuedTraitType willpower, ValuedTraitType[] virtues, ValuedTraitType essence) {
    return createFactorizedTraits(template.personalPool, willpower, virtues, essence);
  }

  public FactorizedTrait[] getPeripheralTraits(ValuedTraitType willpower, ValuedTraitType[] virtues, ValuedTraitType essence) {
    return createFactorizedTraits(template.peripheralPool, willpower, virtues, essence);
  }

  public boolean isEssenceUser() {
    return template.isEssenceUser;
  }
}