package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmLearnAdapter;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmManager;
import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IMultipleEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IPainToleranceCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmVisitor;
import net.sf.anathema.character.main.magic.model.charm.special.ISubEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.MultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.model.charm.ILearningCharmGroup;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.health.HealthModel;
import net.sf.anathema.hero.health.IPainToleranceProvider;
import net.sf.anathema.hero.model.Hero;

import java.util.HashMap;
import java.util.Map;

public class SpecialCharmManager implements ISpecialCharmManager {
  private final Map<Charm, CharmSpecialsModel> specialConfigurationsByCharm = new HashMap<>();
  private final IExtendedCharmLearnableArbitrator arbitrator;
  private CharmSpecialistImpl specialist;
  private Hero hero;
  private final CharmsModel charmsModel;

  public SpecialCharmManager(CharmSpecialistImpl specialist, Hero hero, CharmsModel charmsModel) {
    this.specialist = specialist;
    this.hero = hero;
    this.charmsModel = charmsModel;
    this.arbitrator = charmsModel;
  }

  @Override
  public void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, final Charm charm, final ILearningCharmGroup group) {
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
      public void visitSubEffectCharm(ISubEffectCharm visitedCharm) {
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
  public CharmSpecialsModel getSpecialCharmConfiguration(Charm charm) {
    return specialConfigurationsByCharm.get(charm);
  }

  private void registerTraitCapModifyingCharm(ITraitCapModifyingCharm specialCharm, Charm charm, ILearningCharmGroup group) {
    TraitCapModifyingCharmConfiguration configuration = new TraitCapModifyingCharmConfiguration(specialist, charmsModel, charm, specialCharm);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerEffectMultilearnableCharm(IMultipleEffectCharm visited, Charm charm, ILearningCharmGroup group) {
    MultipleEffectCharmConfiguration configuration = new MultipleEffectCharmConfiguration(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerUpgradableCharm(IUpgradableCharm visited, Charm charm, ILearningCharmGroup group) {
    UpgradableCharmConfiguration configuration = new UpgradableCharmConfiguration(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, visited.requiresBase(), false);
  }

  private void registerMultiLearnableCharm(IMultiLearnableCharm visitedCharm, Charm charm, ILearningCharmGroup group) {
    MultiLearnableCharmConfiguration configuration = new MultiLearnableCharmConfiguration(hero, charmsModel, charm, visitedCharm, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerOxBodyTechnique(IOxBodyTechniqueCharm visited, Charm charm, ILearningCharmGroup group) {
    HealthModel health = specialist.getHealth();
    OxBodyTechniqueSpecialsImpl oxBodyTechniqueConfiguration =
            new OxBodyTechniqueSpecialsImpl(hero, charm, visited.getRelevantTraits(), health.getOxBodyLearnArbitrator(), visited);
    addSpecialCharmConfiguration(charm, group, oxBodyTechniqueConfiguration, true, true);
    health.getOxBodyLearnArbitrator().addOxBodyTechniqueConfiguration(oxBodyTechniqueConfiguration);
    health.addHealthLevelProvider(oxBodyTechniqueConfiguration.getHealthLevelProvider());
  }

  private void registerPainToleranceCharm(final IPainToleranceCharm visitedCharm, Charm charm) {
    final CharmSpecialsModel specialCharmConfiguration = getSpecialCharmConfiguration(charm);
    IPainToleranceProvider painToleranceProvider = new IPainToleranceProvider() {
      @Override
      public int getPainToleranceLevel() {
        int learnCount = specialCharmConfiguration.getCurrentLearnCount();
        return visitedCharm.getPainToleranceLevel(learnCount);
      }
    };
    specialist.getHealth().addPainToleranceProvider(painToleranceProvider);
  }

  private void registerSubeffectCharm(ISubEffectCharm visited, Charm charm, ILearningCharmGroup group) {
    SubEffectCharmSpecialsImpl configuration = new SubEffectCharmSpecialsImpl(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void addSpecialCharmConfiguration(final Charm charm, final ILearningCharmGroup group, final CharmSpecialsModel configuration,
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
      public void charmForgotten(Charm forgottenCharm) {
        if (charm.equals(forgottenCharm)) {
          configuration.forget();
        }
      }

      @Override
      public void charmLearned(Charm learnedCharm) {
        if (charm.equals(learnedCharm)) {
          configuration.learn(group.isLearned(charm, true));
        }
      }
    });
  }
}