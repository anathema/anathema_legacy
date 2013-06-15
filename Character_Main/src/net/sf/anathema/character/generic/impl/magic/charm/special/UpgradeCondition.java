package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.lib.data.Condition;

public class UpgradeCondition implements Condition {
  private final ICharmLearnableArbitrator arbitrator;
  private final ICharm charm;
  private final boolean bpUpgradeAllowed;
  private final IBasicCharacterData data;
  private final Integer essenceMin;
  private final IGenericTraitCollection traitCollection;
  private final Integer traitMin;
  private final ITraitType trait;

  public UpgradeCondition(ICharmLearnableArbitrator arbitrator, ICharm charm, boolean bpUpgradeAllowed, IBasicCharacterData data, Integer essenceMin,
                          IGenericTraitCollection traitCollection, Integer traitMin, ITraitType trait) {
    this.arbitrator = arbitrator;
    this.charm = charm;
    this.bpUpgradeAllowed = bpUpgradeAllowed;
    this.data = data;
    this.essenceMin = essenceMin;
    this.traitCollection = traitCollection;
    this.traitMin = traitMin;
    this.trait = trait;
  }

  @Override
  public boolean isFulfilled() {
    boolean learnable = arbitrator.isLearnable(charm) && (bpUpgradeAllowed || data.isExperienced());
    learnable = !learnable ? learnable : (essenceMin == null || traitCollection.getTrait(OtherTraitType.Essence).getCurrentValue() >= essenceMin);
    learnable = !learnable ? learnable : (traitMin == null || trait == null ||
                                          traitCollection.getTrait(trait).getCurrentValue() >= essenceMin);
    return learnable;
  }
}
