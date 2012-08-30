package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.model.charm.SpecialCharmLearnArbitrator;

public class TraitRequirementChecker {
  private final PrerequisiteTraitChecker prerequisiteTraitChecker;
  private final ICharacterModelContext context;
  private final SpecialCharmLearnArbitrator learnArbitrator;

  public TraitRequirementChecker(PrerequisiteTraitChecker prerequisiteTraitChecker, ICharacterModelContext context, SpecialCharmLearnArbitrator learnArbitrator) {
    this.prerequisiteTraitChecker = prerequisiteTraitChecker;
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
    for (IPrerequisiteModifyingCharm specialCharm : prerequisiteTraitChecker.getPrerequisiteModifyingCharms()) {
      if (learnArbitrator.isLearned(specialCharm.getCharmId())) {
        ITraitType type = prerequisite.getType();
        requiredValue = specialCharm.getTraitModifier(charm, type, requiredValue);
      }
    }
    return prerequisiteTrait.getCurrentValue() >= requiredValue;
  }
}