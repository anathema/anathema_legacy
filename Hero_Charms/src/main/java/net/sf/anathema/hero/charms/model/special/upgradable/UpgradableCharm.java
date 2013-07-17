package net.sf.anathema.hero.charms.model.special.upgradable;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmSpecialist;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffects;
import net.sf.anathema.character.main.magic.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharm;
import net.sf.anathema.lib.data.Condition;

import java.util.Map;

public class UpgradableCharm extends MultipleEffectCharm implements IUpgradableCharm {
  private static final int NO_BP_UPGRADE = -1;

  private final Map<String, Integer> bpCosts;
  private final Map<String, Integer> xpCosts;
  private final Map<String, Integer> essenceMins;
  private final Map<String, Integer> traitMins;
  private final Map<String, TraitType> traits;
  private final boolean requiresBase;

  public UpgradableCharm(String charmId, String[] effectIds, boolean requiresBase, Map<String, Integer> bpCosts, Map<String, Integer> xpCosts,
                         Map<String, Integer> essenceMins, Map<String, Integer> traitMins, Map<String, TraitType> traits) {
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
  public SubEffects buildSubEffects(CharmSpecialist specialist, ICharmLearnableArbitrator arbitrator, Charm charm) {
    UpgradableSubEffects subEffects = new UpgradableSubEffects();
    for (String id : effectIds) {
      Integer bpCost = bpCosts.get(id);
      Integer xpCost = xpCosts.get(id);
      Integer essenceMin = essenceMins.get(id);
      Integer traitMin = traitMins.get(id);
      TraitType trait = traits.get(id);
      Condition learnCondition = buildLearnCondition(arbitrator, specialist, charm, bpCost != null, essenceMin, traitMin, trait);
      Upgrade upgrade = new Upgrade(id, specialist.getExperience(), learnCondition, bpCost == null ? NO_BP_UPGRADE : bpCost, xpCost);
      subEffects.add(upgrade);
    }
    return subEffects;
  }

  private Condition buildLearnCondition(final ICharmLearnableArbitrator arbitrator, CharmSpecialist specialist, final Charm charm,
                                        final boolean bpUpgradeAllowed, final Integer essenceMin, final Integer traitMin, final TraitType trait) {
    return new UpgradeCondition(arbitrator, charm, bpUpgradeAllowed, specialist, essenceMin, traitMin, trait);
  }

  @Override
  public boolean requiresBase() {
    return requiresBase;
  }
}