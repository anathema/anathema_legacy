package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.model.charm.ICharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public interface ISpecialCharmManager {

  public void registerOxBodyTechnique(
      IOxBodyTechniqueCharm visited,
      ICharm charm,
      IGenericTrait relevantTrait,
      ILearningCharmGroup group);

  public void registerMultiLearnableCharm(
      IMultiLearnableCharm visitedCharm,
      final ICharm charm,
      ILearningCharmGroup group,
      ICharmLearnableArbitrator arbitrator);

  public void registerPainToleranceCharm(IPainToleranceCharm visitedCharm, ICharm charm, ILearningCharmGroup group);

}
