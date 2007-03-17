package net.sf.anathema.character.impl.model.charm.special;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.impl.model.charm.CharmConfiguration;
import net.sf.anathema.character.impl.model.charm.ISpecialCharmManager;
import net.sf.anathema.character.model.charm.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.health.IPainToleranceProvider;

public class SpecialCharmManager implements ISpecialCharmManager {
  private final Map<ICharm, ISpecialCharmConfiguration> specialConfigurationsByCharm = new HashMap<ICharm, ISpecialCharmConfiguration>();
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
      IExtendedCharmLearnableArbitrator arbitrator) {
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
    final ISpecialCharmConfiguration specialCharmConfiguration = getSpecialCharmConfiguration(charm);
    IPainToleranceProvider painToleranceProvider = new IPainToleranceProvider() {
      public int getPainToleranceLevel() {
        int learnCount = specialCharmConfiguration.getCurrentLearnCount();
        return visitedCharm.getPainToleranceLevel(learnCount);
      }
    };
    health.addPainToleranceProvider(painToleranceProvider);
  }

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm) {
    return specialConfigurationsByCharm.get(charm);
  }

  @Override
  public boolean hasSpecialCharmConfiguration(ICharm charm) {
    return specialConfigurationsByCharm.containsKey(charm);
  }

  public void registerSubeffectCharm(
      ISubeffectCharm visited,
      ICharm charm,
      ILearningCharmGroup group,
      IExtendedCharmLearnableArbitrator arbitrator) {
    addSpecialCharmConfiguration(charm, group, new SubeffectCharmConfiguration(context, charm, visited, arbitrator));
  }

  public void registerEffectMultilearnableCharm(
      IMultipleEffectCharm visited,
      ICharm charm,
      ILearningCharmGroup group,
      CharmConfiguration arbitrator) {
    addSpecialCharmConfiguration(
        charm,
        group,
        new MultipleEffectCharmConfiguration(context, charm, visited, arbitrator));
  }

  public void addSpecialCharmConfiguration(
      final ICharm charm,
      final ILearningCharmGroup group,
      ISpecialCharmConfiguration configuration) {
    if (specialConfigurationsByCharm.containsKey(charm)) {
      throw new IllegalArgumentException("Special configuration already defined for charm " + charm.getId()); //$NON-NLS-1$
    }
    specialConfigurationsByCharm.put(charm, configuration);
    configuration.addSpecialCharmLearnListener(group.createSpecialCharmLearnListenerFor(charm));
  }
}