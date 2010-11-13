package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IGenericCharmConfiguration {

  public String[] getUncompletedCelestialMartialArtsGroups();

  public ICharm[] getLearnedCharms();
}