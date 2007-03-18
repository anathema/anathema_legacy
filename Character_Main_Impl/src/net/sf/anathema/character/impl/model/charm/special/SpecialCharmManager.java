package net.sf.anathema.character.impl.model.charm.special;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.impl.model.charm.ISpecialCharmManager;
import net.sf.anathema.character.model.charm.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.health.IPainToleranceProvider;

public class SpecialCharmManager implements ISpecialCharmManager {
  private final Map<ICharm, ISpecialCharmConfiguration> specialConfigurationsByCharm = new HashMap<ICharm, ISpecialCharmConfiguration>();
  private final IHealthConfiguration health;
  private final ICharacterModelContext context;
  private final IExtendedCharmLearnableArbitrator arbitrator;

  public SpecialCharmManager(
      IExtendedCharmLearnableArbitrator arbitrator,
      IHealthConfiguration health,
      ICharacterModelContext context) {
    this.arbitrator = arbitrator;
    this.health = health;
    this.context = context;
  }

  private void addSpecialCharmConfiguration(
      final ICharm charm,
      final ILearningCharmGroup group,
      ISpecialCharmConfiguration configuration) {
    if (specialConfigurationsByCharm.containsKey(charm)) {
      throw new IllegalArgumentException("Special configuration already defined for charm " + charm.getId()); //$NON-NLS-1$
    }
    specialConfigurationsByCharm.put(charm, configuration);
    configuration.addSpecialCharmLearnListener(group.createSpecialCharmLearnListenerFor(charm));
  }

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm) {
    return specialConfigurationsByCharm.get(charm);
  }

  @Override
  public boolean hasSpecialCharmConfiguration(ICharm charm) {
    return specialConfigurationsByCharm.containsKey(charm);
  }

  private void registerEffectMultilearnableCharm(IMultipleEffectCharm visited, ICharm charm, ILearningCharmGroup group) {
    addSpecialCharmConfiguration(
        charm,
        group,
        new MultipleEffectCharmConfiguration(context, charm, visited, arbitrator));
  }

  private void registerMultiLearnableCharm(IMultiLearnableCharm visitedCharm, ICharm charm, ILearningCharmGroup group) {
    addSpecialCharmConfiguration(charm, group, new MultiLearnableCharmConfiguration(
        context,
        charm,
        visitedCharm,
        arbitrator));
  }

  private void registerOxBodyTechnique(IOxBodyTechniqueCharm visited, ICharm charm, ILearningCharmGroup group) {
    IGenericTrait relevantTrait = context.getTraitCollection().getTrait(visited.getRelevantTrait());
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

  private void registerPainToleranceCharm(final IPainToleranceCharm visitedCharm, ICharm charm) {
    final ISpecialCharmConfiguration specialCharmConfiguration = getSpecialCharmConfiguration(charm);
    IPainToleranceProvider painToleranceProvider = new IPainToleranceProvider() {
      public int getPainToleranceLevel() {
        int learnCount = specialCharmConfiguration.getCurrentLearnCount();
        return visitedCharm.getPainToleranceLevel(learnCount);
      }
    };
    health.addPainToleranceProvider(painToleranceProvider);
  }

  @Override
  public void registerSpecialCharmConfiguration(
      ISpecialCharm specialCharm,
      final ICharm charm,
      final ILearningCharmGroup group) {
    specialCharm.accept(new ISpecialCharmVisitor() {
      public void visitMultiLearnableCharm(IMultiLearnableCharm visitedCharm) {
        registerMultiLearnableCharm(visitedCharm, charm, group);
      }

      public void visitMultipleEffectCharm(IMultipleEffectCharm visitedCharm) {
        registerEffectMultilearnableCharm(visitedCharm, charm, group);
      }

      public void visitOxBodyTechnique(IOxBodyTechniqueCharm visitedCharm) {
        registerOxBodyTechnique(visitedCharm, charm, group);
      }

      public void visitPainToleranceCharm(IPainToleranceCharm visitedCharm) {
        registerPainToleranceCharm(visitedCharm, charm);
      }

      public void visitSubeffectCharm(ISubeffectCharm visitedCharm) {
        registerSubeffectCharm(visitedCharm, charm, group);
      }
    });
  }

  private void registerSubeffectCharm(ISubeffectCharm visited, ICharm charm, ILearningCharmGroup group) {
    addSpecialCharmConfiguration(charm, group, new SubeffectCharmConfiguration(context, charm, visited, arbitrator));
  }
}