package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.impl.model.charm.CharmSpecialistImpl;
import net.sf.anathema.character.impl.model.charm.ISpecialCharmManager;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.character.main.model.health.IPainToleranceProvider;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.CharmModel;
import net.sf.anathema.character.model.charm.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

import java.util.HashMap;
import java.util.Map;

public class SpecialCharmManager implements ISpecialCharmManager {
  private final Map<ICharm, ISpecialCharmConfiguration> specialConfigurationsByCharm = new HashMap<>();
  private final IExtendedCharmLearnableArbitrator arbitrator;
  private CharmSpecialistImpl specialist;
  private Hero hero;
  private final CharmModel charmModel;

  public SpecialCharmManager(CharmSpecialistImpl specialist, Hero hero, CharmModel charmModel) {
    this.specialist = specialist;
    this.hero = hero;
    this.charmModel = charmModel;
    this.arbitrator = charmModel;
  }

  @Override
  public void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, final ICharm charm, final ILearningCharmGroup group) {
    specialCharm.accept(new ISpecialCharmVisitor() {
      @Override
      public void visitMultiLearnableCharm(IMultiLearnableCharm visitedCharm) {
        registerMultiLearnableCharm(visitedCharm, charm, group);
      }

      @Override
      public void visitMultipleEffectCharm(IMultipleEffectCharm visitedCharm) {
        registerEffectMultilearnableCharm(visitedCharm, charm, group);
      }

      @Override
      public void visitOxBodyTechnique(IOxBodyTechniqueCharm visitedCharm) {
        registerOxBodyTechnique(visitedCharm, charm, group);
      }

      @Override
      public void visitPainToleranceCharm(IPainToleranceCharm visitedCharm) {
        registerPainToleranceCharm(visitedCharm, charm);
      }

      @Override
      public void visitSubeffectCharm(ISubeffectCharm visitedCharm) {
        registerSubeffectCharm(visitedCharm, charm, group);
      }

      @Override
      public void visitUpgradableCharm(IUpgradableCharm visitedCharm) {
        registerUpgradableCharm(visitedCharm, charm, group);
      }

      @Override
      public void visitPrerequisiteModifyingCharm(IPrerequisiteModifyingCharm visitedCharm) {
        // do nothing
      }

      @Override
      public void visitTraitCapModifyingCharm(ITraitCapModifyingCharm visitedCharm) {
        registerTraitCapModifyingCharm(visitedCharm, charm, group);
      }
    });
  }

  @Override
  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm) {
    return specialConfigurationsByCharm.get(charm);
  }

  private void registerTraitCapModifyingCharm(ITraitCapModifyingCharm specialCharm, ICharm charm, ILearningCharmGroup group) {
    TraitCapModifyingCharmConfiguration configuration = new TraitCapModifyingCharmConfiguration(specialist, charmModel, charm, specialCharm);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerEffectMultilearnableCharm(IMultipleEffectCharm visited, ICharm charm, ILearningCharmGroup group) {
    MultipleEffectCharmConfiguration configuration = new MultipleEffectCharmConfiguration(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerUpgradableCharm(IUpgradableCharm visited, ICharm charm, ILearningCharmGroup group) {
    UpgradableCharmConfiguration configuration = new UpgradableCharmConfiguration(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, visited.requiresBase(), false);
  }

  private void registerMultiLearnableCharm(IMultiLearnableCharm visitedCharm, ICharm charm, ILearningCharmGroup group) {
    MultiLearnableCharmConfiguration configuration = new MultiLearnableCharmConfiguration(hero, charmModel, charm, visitedCharm, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerOxBodyTechnique(IOxBodyTechniqueCharm visited, ICharm charm, ILearningCharmGroup group) {
    HealthModel health = specialist.getHealth();
    OxBodyTechniqueConfiguration oxBodyTechniqueConfiguration =
            new OxBodyTechniqueConfiguration(hero, charm, visited.getRelevantTraits(), health.getOxBodyLearnArbitrator(), visited);
    addSpecialCharmConfiguration(charm, group, oxBodyTechniqueConfiguration, true, true);
    health.getOxBodyLearnArbitrator().addOxBodyTechniqueConfiguration(oxBodyTechniqueConfiguration);
    health.addHealthLevelProvider(oxBodyTechniqueConfiguration.getHealthLevelProvider());
  }

  private void registerPainToleranceCharm(final IPainToleranceCharm visitedCharm, ICharm charm) {
    final ISpecialCharmConfiguration specialCharmConfiguration = getSpecialCharmConfiguration(charm);
    IPainToleranceProvider painToleranceProvider = new IPainToleranceProvider() {
      @Override
      public int getPainToleranceLevel() {
        int learnCount = specialCharmConfiguration.getCurrentLearnCount();
        return visitedCharm.getPainToleranceLevel(learnCount);
      }
    };
    specialist.getHealth().addPainToleranceProvider(painToleranceProvider);
  }

  private void registerSubeffectCharm(ISubeffectCharm visited, ICharm charm, ILearningCharmGroup group) {
    SubeffectCharmConfiguration configuration = new SubeffectCharmConfiguration(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void addSpecialCharmConfiguration(final ICharm charm, final ILearningCharmGroup group, final ISpecialCharmConfiguration configuration,
                                            boolean learnListener, final boolean forgetAtZero) {
    if (specialConfigurationsByCharm.containsKey(charm)) {
      throw new IllegalArgumentException("Special configuration already defined for charm " + charm.getId());
    }
    specialConfigurationsByCharm.put(charm, configuration);
    if (learnListener) {
      configuration.addSpecialCharmLearnListener(new ISpecialCharmLearnListener() {
        @Override
        public void learnCountChanged(int newValue) {
          if (!hero.isFullyLoaded()) {
            return;
          }
          if (newValue == 0) {
            if (forgetAtZero) {
              group.forgetCharm(charm, group.isLearned(charm, true));
            } else {
              group.fireRecalculateRequested();
            }
          } else {
            if (!group.isLearned(charm)) {
              group.toggleLearned(charm);
            }
            group.fireRecalculateRequested();
          }
        }
      });
    }
    group.addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(ICharm forgottenCharm) {
        if (charm.equals(forgottenCharm)) {
          configuration.forget();
        }
      }

      @Override
      public void charmLearned(ICharm learnedCharm) {
        if (charm.equals(learnedCharm)) {
          configuration.learn(group.isLearned(charm, true));
        }
      }
    });
  }
}