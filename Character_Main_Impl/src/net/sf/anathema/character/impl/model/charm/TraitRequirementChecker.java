package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.model.charm.SpecialCharmLearnArbitrator;

public class TraitRequirementChecker {
  private final PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private final ICharacterModelContext context;
  private final SpecialCharmLearnArbitrator learnArbitrator;

  public TraitRequirementChecker(PrerequisiteModifyingCharms prerequisiteModifyingCharms, ICharacterModelContext context, SpecialCharmLearnArbitrator learnArbitrator) {
    this.prerequisiteModifyingCharms = prerequisiteModifyingCharms;
    this.context = context;
    this.learnArbitrator = learnArbitrator;
  }

  @SuppressWarnings("RedundantIfStatement")
  public boolean areTraitMinimumsSatisfied(ICharm charm) {
    for (IGenericTrait prerequisite : charm.getPrerequisites()) {
      if (!isMinimumSatisfied(charm, prerequisite)) {
        return false;
      }
    }
    if (!isMinimumSatisfied(charm, charm.getEssence())) {
      return false;
    }
    return true;
  }

  private boolean isMinimumSatisfied(ICharm charm, IGenericTrait prerequisite) {
    IGenericTrait prerequisiteTrait = context.getTraitCollection().getTrait(prerequisite.getType());
    if (prerequisiteTrait == null) {
      return false;
    }
    int requiredValue = prerequisite.getCurrentValue();
    for (IPrerequisiteModifyingCharm modifier : prerequisiteModifyingCharms.getPrerequisiteModifyingCharms()) {
      if (learnArbitrator.isLearned(modifier.getCharmId())) {
        requiredValue = modifier.modifyRequiredValue(charm, requiredValue);
      }
    }
    return prerequisiteTrait.getCurrentValue() >= requiredValue;
  }
}