package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.generic.magic.charms.special.SubEffects;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.data.ICondition;

import java.util.Map;

public class UpgradableCharm extends MultipleEffectCharm implements IUpgradableCharm {
  private static final int NO_BP_UPGRADE = -1;

  private final Map<String, Integer> bpCosts;
  private final Map<String, Integer> xpCosts;
  private final Map<String, Integer> essenceMins;
  private final Map<String, Integer> traitMins;
  private final Map<String, ITraitType> traits;
  private final boolean requiresBase;

  public UpgradableCharm(String charmId, String[] effectIds, boolean requiresBase, Map<String, Integer> bpCosts,
                         Map<String, Integer> xpCosts, Map<String, Integer> essenceMins, Map<String, Integer> traitMins,
                         Map<String, ITraitType> traits) {
    super(charmId, effectIds);
    this.bpCosts = bpCosts;
    this.xpCosts = xpCosts;
    this.essenceMins = essenceMins;
    this.traitMins = traitMins;
    this.traits = traits;
    this.requiresBase = requiresBase;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitUpgradableCharm(this);
  }

  @Override
  public SubEffects buildSubeffects(IBasicCharacterData data, IGenericTraitCollection traitCollection,
                                    ICharmLearnableArbitrator arbitrator, ICharm charm) {
    UpgradableSubEffects subEffects = new UpgradableSubEffects();
    for (String id : effectIds) {
      Integer bpCost = bpCosts.get(id);
      Integer xpCost = xpCosts.get(id);
      Integer essenceMin = essenceMins.get(id);
      Integer traitMin = traitMins.get(id);
      ITraitType trait = traits.get(id);
      Upgrade upgrade = new Upgrade(id, data,
              buildLearnCondition(arbitrator, data, traitCollection, charm, bpCost != null, essenceMin, traitMin,
                      trait), bpCost == null ? NO_BP_UPGRADE : bpCost, xpCost);
      subEffects.add(upgrade);
    }
    return subEffects;
  }

  private ICondition buildLearnCondition(final ICharmLearnableArbitrator arbitrator, final IBasicCharacterData data,
                                         final IGenericTraitCollection traitCollection, final ICharm charm,
                                         final boolean bpUpgradeAllowed, final Integer essenceMin,
                                         final Integer traitMin, final ITraitType trait) {
    return new UpgradeCondition(arbitrator, charm, bpUpgradeAllowed, data, essenceMin, traitCollection, traitMin,
            trait);
  }

  @Override
  public boolean requiresBase() {
    return requiresBase;
  }
}