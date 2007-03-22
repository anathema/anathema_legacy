package net.sf.anathema.dummy.character.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;

public final class DummyCharmContext implements ICharmContext {

  private final IGenericCharacter character;
  private final ICharmLearnStrategy learnStrategy;

  public DummyCharmContext(IGenericCharacter character, ICharmLearnStrategy learnStrategy) {
    this.character = character;
    this.learnStrategy = learnStrategy;
  }

  public boolean isLearned(ICharm charm) {
    return character.isLearned(charm);
  }

  public ICharmLearnStrategy getCharmLearnStrategy() {
    return learnStrategy;
  }

  public final boolean isRequirementFulfilled(ICharmAttributeRequirement requirement) {
    return character.isRequirementFulfilled(requirement);
  }

  public final String[] getUncompletedCelestialMartialArtsGroups() {
    return character.getUncompletedCelestialMartialArtsGroups();
  }
}