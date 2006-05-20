package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.impl.model.charm.ISpecialCharmManager;
import net.sf.anathema.character.model.charm.ICharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.health.IPainToleranceProvider;

public class SpecialCharmManager implements ISpecialCharmManager {

  private final IHealthConfiguration health;
  private final ICharacterModelContext context;

  public SpecialCharmManager(IHealthConfiguration health, ICharacterModelContext context) {
    this.health = health;
    this.context = context;
  }

  public void registerMultiLearnableCharm(
      IMultiLearnableCharm visitedCharm,
      final ICharm charm,
      ILearningCharmGroup group,
      ICharmLearnableArbitrator arbitrator) {
    addSpecialCharmConfiguration(charm, group, new MultiLearnableCharmConfiguration(
        context,
        charm,
        visitedCharm,
        arbitrator));
  }

  public void registerOxBodyTechnique(
      IOxBodyTechniqueCharm visited,
      final ICharm charm,
      IGenericTrait relevantTrait,
      ILearningCharmGroup group) {
    OxBodyTechniqueConfiguration oxBodyTechniqueConfiguration = new OxBodyTechniqueConfiguration(
        context.getTraitContext(),
        charm,
        relevantTrait,
        health.getOxBodyLearnArbitrator(),
        visited);
    addSpecialCharmConfiguration(charm, group, oxBodyTechniqueConfiguration);
    health.getOxBodyLearnArbitrator().addOxBodyTechniqueConfiguration(oxBodyTechniqueConfiguration);
    health.addHealthLevelProvider(oxBodyTechniqueConfiguration.getHealthLevelProvider());
  }

  public void registerPainToleranceCharm(final IPainToleranceCharm visitedCharm, ICharm charm, ILearningCharmGroup group) {
    final ISpecialCharmConfiguration specialCharmConfiguration = group.getSpecialCharmConfiguration(charm);
    IPainToleranceProvider painToleranceProvider = new IPainToleranceProvider() {
      public int getPainToleranceLevel() {
        int learnCount = specialCharmConfiguration.getCurrentLearnCount();
        return visitedCharm.getPainToleranceLevel(learnCount);
      }
    };
    health.addPainToleranceProvider(painToleranceProvider);
  }

  private void addSpecialCharmConfiguration(
      ICharm charm,
      ILearningCharmGroup group,
      ISpecialCharmConfiguration configuration) {
    group.addSpecialCharmConfiguration(charm, configuration);
  }
}