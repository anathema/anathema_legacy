package net.sf.anathema.character.generic.impl.magic.charm;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;

public class GenericCharmConfiguration implements IGenericCharmConfiguration {

  private final IGenericCharacter character;

  public GenericCharmConfiguration(IGenericCharacter character) {
    this.character = character;
  }

  public final boolean isRequirementFulfilled(ICharmAttributeRequirement requirement) {
    return character.isRequirementFulfilled(requirement);
  }

  public final String[] getUncompletedCelestialMartialArtsGroups() {
    return character.getUncompletedCelestialMartialArtsGroups();
  }
}