package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;

public interface IGenericCharmConfiguration {

  public boolean isRequirementFulfilled(ICharmAttributeRequirement requirement);

  public String[] getUncompletedCelestialMartialArtsGroups();
}