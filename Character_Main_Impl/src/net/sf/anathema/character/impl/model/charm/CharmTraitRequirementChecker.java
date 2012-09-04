package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.TraitRequirementChecker;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.model.charm.SpecialCharmLearnArbitrator;

public class CharmTraitRequirementChecker implements TraitRequirementChecker {
  private final PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private final ICharacterModelContext context;
  private final SpecialCharmLearnArbitrator learnArbitrator;

  public CharmTraitRequirementChecker(PrerequisiteModifyingCharms prerequisiteModifyingCharms,
                                      ICharacterModelContext context, SpecialCharmLearnArbitrator learnArbitrator) {
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

  @Override
  public boolean isMinimumSatisfied(ICharm charm, IGenericTrait prerequisite) {
    IGenericTrait actualTrait = context.getTraitCollection().getTrait(prerequisite.getType());
    if (actualTrait == null) {
      return false;
    }
    int requiredValue = prerequisite.getCurrentValue();
    for (IPrerequisiteModifyingCharm modifier : prerequisiteModifyingCharms.getPrerequisiteModifyingCharms()) {
      if (learnArbitrator.isLearned(modifier.getCharmId())) {
        requiredValue = modifier.modifyRequiredValue(charm, requiredValue);
      }
    }
    return actualTrait.getCurrentValue() >= requiredValue;
  }
}