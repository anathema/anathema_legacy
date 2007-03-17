package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.model.charm.IExtendedCharmLearnableArbitrator;
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
      IExtendedCharmLearnableArbitrator arbitrator);

  public void registerPainToleranceCharm(IPainToleranceCharm visitedCharm, ICharm charm, ILearningCharmGroup group);

  public void registerSubeffectCharm(
      ISubeffectCharm visited,
      ICharm charm,
      ILearningCharmGroup group,
      IExtendedCharmLearnableArbitrator arbitrator);

  public void registerEffectMultilearnableCharm(
      IMultipleEffectCharm visited,
      ICharm charm,
      ILearningCharmGroup group,
      CharmConfiguration arbitrator);

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm);

  public boolean hasSpecialCharmConfiguration(ICharm charm);
}