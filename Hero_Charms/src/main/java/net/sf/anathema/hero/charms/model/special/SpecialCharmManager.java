package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.learn.ILearningCharmGroup;
import net.sf.anathema.hero.charms.model.special.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.oxbody.IOxBodyTechniqueCharm;
import net.sf.anathema.hero.charms.model.special.paintolerance.IPainToleranceCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.IPrerequisiteModifyingCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.ISubEffectCharm;
import net.sf.anathema.hero.charms.model.special.traitcap.ITraitCapModifyingCharm;
import net.sf.anathema.hero.charms.model.special.upgradable.IUpgradableCharm;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.special.multilearn.MultiLearnableCharmSpecialsImpl;
import net.sf.anathema.hero.charms.model.special.oxbody.OxBodyTechniqueSpecialsImpl;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecialsImpl;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharmSpecialsImpl;
import net.sf.anathema.hero.charms.model.special.traitcap.TraitCapModifyingCharmConfiguration;
import net.sf.anathema.hero.charms.model.special.upgradable.UpgradableCharmConfiguration;
import net.sf.anathema.hero.health.model.HealthModel;
import net.sf.anathema.hero.health.model.HealthModelFetcher;
import net.sf.anathema.hero.health.model.IPainToleranceProvider;
import net.sf.anathema.hero.charms.model.special.oxbody.OxBodyTechniqueArbitratorImpl;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;

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
        registerSubEffectCharm(visitedCharm, charm, group);
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
    MultipleEffectCharmSpecialsImpl configuration = new MultipleEffectCharmSpecialsImpl(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerUpgradableCharm(IUpgradableCharm visited, Charm charm, ILearningCharmGroup group) {
    UpgradableCharmConfiguration configuration = new UpgradableCharmConfiguration(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, visited.requiresBase(), false);
  }

  private void registerMultiLearnableCharm(IMultiLearnableCharm visitedCharm, Charm charm, ILearningCharmGroup group) {
    MultiLearnableCharmSpecialsImpl configuration = new MultiLearnableCharmSpecialsImpl(hero, charmsModel, charm, visitedCharm, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerOxBodyTechnique(IOxBodyTechniqueCharm visited, Charm charm, ILearningCharmGroup group) {
    HealthModel health = specialist.getHealth();
    OxBodyTechniqueArbitratorImpl arbitrator = createArbitrator();
    TraitType[] relevantTraits = visited.getRelevantTraits();
    OxBodyTechniqueSpecialsImpl specials = new OxBodyTechniqueSpecialsImpl(hero, charm, relevantTraits, arbitrator, visited);
    addSpecialCharmConfiguration(charm, group, specials, true, true);
    arbitrator.addOxBodyTechniqueConfiguration(specials);
    health.addHealthLevelProvider(specials.getHealthLevelProvider());
  }

  private OxBodyTechniqueArbitratorImpl createArbitrator() {
    HealthModel healthModel = HealthModelFetcher.fetch(hero);
    TraitType[] toughnessControllingTraitTypes = healthModel.getToughnessControllingTraitTypes();
    return new OxBodyTechniqueArbitratorImpl(TraitModelFetcher.fetch(hero).getTraits(toughnessControllingTraitTypes));
  }

  private void registerPainToleranceCharm(final IPainToleranceCharm visitedCharm, Charm charm) {
    final CharmSpecialsModel charmSpecialsModel = getSpecialCharmConfiguration(charm);
    IPainToleranceProvider painToleranceProvider = new IPainToleranceProvider() {
      @Override
      public int getPainToleranceLevel() {
        int learnCount = charmSpecialsModel.getCurrentLearnCount();
        return visitedCharm.getPainToleranceLevel(learnCount);
      }
    };
    specialist.getHealth().addPainToleranceProvider(painToleranceProvider);
  }

  private void registerSubEffectCharm(ISubEffectCharm visited, Charm charm, ILearningCharmGroup group) {
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