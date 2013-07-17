package net.sf.anathema.hero.charms.model;

import com.google.common.base.Functions;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmAttributeList;
import net.sf.anathema.character.main.magic.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.charm.CharmLearnAdapter;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.character.main.magic.charm.GroupedCharmIdMap;
import net.sf.anathema.character.main.magic.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.charm.ICharmLearnListener;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharmManager;
import net.sf.anathema.character.main.magic.charm.IndirectCharmRequirement;
import net.sf.anathema.character.main.magic.charm.PrerequisiteModifyingCharms;
import net.sf.anathema.character.main.magic.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.MultiLearnCharmSpecials;
import net.sf.anathema.character.main.magic.charm.special.MultipleEffectCharmSpecials;
import net.sf.anathema.character.main.magic.charm.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.charm.ILearningCharmGroupContainer;
import net.sf.anathema.character.main.magic.charmtree.CharmTraitRequirementChecker;
import net.sf.anathema.character.main.magic.charmtree.GroupCharmTree;
import net.sf.anathema.character.main.magic.sheet.content.IMagicStats;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.model.context.CreationCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.context.ExperiencedCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.context.ProxyCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.options.MartialArtsOptions;
import net.sf.anathema.hero.charms.model.options.NonMartialArtsOptions;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charms.model.rules.CharmsRulesImpl;
import net.sf.anathema.hero.charms.model.special.SpecialCharmManager;
import net.sf.anathema.hero.charms.sheet.content.PrintCharmsProvider;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.charmtree.martial.MartialArtsLevel;
import net.sf.anathema.charms.MartialArtsUtilities;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModel;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModelFetcher;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.lang3.ArrayUtils;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.hero.charmtree.martial.MartialArtsLevel.Sidereal;
import static net.sf.anathema.charms.MartialArtsUtilities.hasLevel;
import static net.sf.anathema.charms.MartialArtsUtilities.isFormMagic;
import static net.sf.anathema.charms.MartialArtsUtilities.isMartialArts;

public class CharmsModelImpl implements CharmsModel {

  private final ProxyCharmLearnStrategy charmLearnStrategy = new ProxyCharmLearnStrategy(new CreationCharmLearnStrategy());
  private final CharmsRules charmsRules;
  private ISpecialCharmManager manager;
  private ILearningCharmGroupContainer learningCharmGroupContainer = new ILearningCharmGroupContainer() {
    @Override
    public ILearningCharmGroup getLearningCharmGroup(Charm charm) {
      return getGroup(charm);
    }
  };
  private ILearningCharmGroup[] martialArtsGroups;
  private final Map<Identifier, ILearningCharmGroup[]> nonMartialArtsGroupsByType = new HashMap<>();
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private CharmProvider provider;
  private ExperienceModel experience;
  private TraitModel traits;
  private PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private MartialArtsOptions martialArtsOptions;
  private NonMartialArtsOptions nonMartialArtsOptions;
  private Hero hero;
  private final List<PrintMagicProvider> printMagicProviders = new ArrayList<>();
  private final List<MagicLearner> magicLearners = new ArrayList<>();

  public CharmsModelImpl(CharmsTemplate charmsTemplate) {
    this.charmsRules = new CharmsRulesImpl(charmsTemplate);
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    CharmSpecialistImpl specialist = new CharmSpecialistImpl(hero);
    this.experience = ExperienceModelFetcher.fetch(hero);
    this.traits = TraitModelFetcher.fetch(hero);
    this.hero = hero;
    this.provider = environment.getDataSet(CharmCache.class).getCharmProvider();
    this.martialArtsOptions = new MartialArtsOptions(provider, charmsRules);
    this.nonMartialArtsOptions = new NonMartialArtsOptions(hero, environment.getCharacterTypes(), provider, charmsRules);
    this.manager = new SpecialCharmManager(specialist, hero, this);
    this.martialArtsGroups = createGroups(martialArtsOptions.getAllCharmGroups());
    initNonMartialArtsGroups();
    initSpecialCharmConfigurations();
    addCompulsiveCharms(hero.getTemplate());
    addOverdrivePools(hero);
    addPrintProvider(new PrintCharmsProvider(hero));
    addLearnProvider(new CharmLearner(this));
  }

  private void addOverdrivePools(Hero hero) {
    EssencePoolModel poolModel = EssencePoolModelFetcher.fetch(hero);
    if (poolModel == null) {
      return;
    }
    poolModel.addOverdrivePool(new CharmOverdrivePool(this, experience));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (ILearningCharmGroup group : getAllGroups()) {
      group.addCharmLearnListener(new CharmLearnAdapter() {
        @Override
        public void charmForgotten(Charm charm) {
          control.announce().changeOccurred();
        }

        @Override
        public void charmLearned(Charm charm) {
          control.announce().changeOccurred();
        }
      });
    }
    this.experience.addStateChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        if (experience.isExperienced()) {
          charmLearnStrategy.setStrategy(new ExperiencedCharmLearnStrategy());
        } else {
          charmLearnStrategy.setStrategy(new CreationCharmLearnStrategy());
        }
      }
    });
    announcer.addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        verifyCharms();
        control.announce().changeOccurred();
      }
    });
    addCharmLearnListener(new CharacterChangeCharmListener(announcer));
  }

  @SuppressWarnings("UnusedParameters")
  private void addCompulsiveCharms(HeroTemplate template) {
    String[] compulsiveCharms = getCompulsiveCharmIds();

    for (String charmId : compulsiveCharms) {
      Charm charm = getCharmById(charmId);
      getGroup(charm).learnCharm(charm, false);
    }
  }

  private void initNonMartialArtsGroups() {
    Iterable<CharacterType> availableCharacterTypes = nonMartialArtsOptions.getAvailableCharacterTypes();
    for (CharacterType characterType : availableCharacterTypes) {
      initLearnGroupForCharacterType(characterType, nonMartialArtsOptions.getCharmTrees(characterType));
    }
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    for (ILearningCharmGroup group : getAllGroups()) {
      group.addCharmLearnListener(listener);
    }
  }

  @Override
  public CharmIdMap getCharmIdMap() {
    List<CharmIdMap> trees = new ArrayList<>();
    trees.add(nonMartialArtsOptions);
    trees.add(martialArtsOptions);
    return new GroupedCharmIdMap(trees);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    return provider.getSpecialCharms(martialArtsOptions, getCharmIdMap(), nonMartialArtsOptions.getNativeCharacterType());
  }

  private void initSpecialCharmConfigurations() {
    CharmIdMap charmIdMap = getCharmIdMap();
    ISpecialCharm[] specialCharms = getSpecialCharms();
    for (ISpecialCharm specialCharm : specialCharms) {
      Charm charm = charmIdMap.getCharmById(specialCharm.getCharmId());
      if (charm == null) {
        continue;
      }
      ILearningCharmGroup group = getGroupById(charm.getCharacterType(), charm.getGroupId());
      manager.registerSpecialCharmConfiguration(specialCharm, charm, group);
    }
  }

  private ILearningCharmGroup[] createGroups(ICharmGroup[] charmGroups) {
    List<ILearningCharmGroup> newGroups = new ArrayList<>();
    ICharmLearnListener mergedListener = new CharmLearnAdapter() {
      @Override
      public void charmLearned(Charm charm) {
        learnOtherCharmsFromMerge(charm);
        learnDirectChildrenActivatedViaThereMerge(charm);
      }

      private void learnDirectChildrenActivatedViaThereMerge(Charm charm) {
        for (Charm child : charm.getLearnChildCharms()) {
          boolean learnedMerged = false;
          for (Charm mergedCharm : child.getMergedCharms()) {
            learnedMerged = learnedMerged || isLearned(mergedCharm);
          }
          if (learnedMerged && isLearnable(child)) {
            getGroup(child).learnCharm(child, isExperienced());
          }
        }
      }

      private void learnOtherCharmsFromMerge(Charm charm) {
        for (Charm mergedCharm : charm.getMergedCharms()) {
          if (!isLearned(mergedCharm) && isLearnableWithoutPrerequisites(mergedCharm) &&
                  CharmsModelImpl.this.getCharmSpecialsModel(mergedCharm) == null) {
            getGroup(mergedCharm).learnCharm(mergedCharm, isExperienced());
          }
        }
      }

      @Override
      public void charmForgotten(Charm charm) {
        for (Charm mergedCharm : charm.getMergedCharms()) {
          if (isLearned(mergedCharm)) {
            getGroup(mergedCharm).forgetCharm(mergedCharm, isExperienced());
          }
        }
      }
    };
    ICharmLearnListener specialCharmListener = new CharmLearnAdapter() {

      @Override
      public void recalculateRequested() {
        for (Charm charm : getLearnedCharms(true)) {
          boolean prereqsMet = true;
          for (Charm parent : charm.getParentCharms()) {
            for (String subeffectRequirement : charm.getParentSubEffects()) {
              if (getSubeffectParent(subeffectRequirement).equals(parent.getId())) {
                CharmSpecialsModel config = getSpecialCharmConfiguration(getSubeffectParent(subeffectRequirement));
                if (config instanceof MultipleEffectCharmSpecials) {
                  MultipleEffectCharmSpecials mConfig = (MultipleEffectCharmSpecials) config;
                  prereqsMet = prereqsMet && mConfig.getEffectById(getSubeffect(subeffectRequirement)).isLearned();
                }
                if (config instanceof MultiLearnCharmSpecials) {
                  MultiLearnCharmSpecials mConfig = (MultiLearnCharmSpecials) config;
                  String effect = getSubeffect(subeffectRequirement);
                  int requiredCount = Integer.parseInt(effect.replace("Repurchase", ""));
                  prereqsMet = mConfig.getCurrentLearnCount() >= requiredCount;
                }
              }
            }
          }
          if (!prereqsMet) {
            getGroup(charm).forgetCharm(charm, isExperienced());
          }
        }
      }
    };
    for (ICharmGroup charmGroup : charmGroups) {
      ILearningCharmGroup group = new LearningCharmGroup(charmLearnStrategy, charmGroup, this, learningCharmGroupContainer, this);
      newGroups.add(group);

      group.addCharmLearnListener(mergedListener);
      group.addCharmLearnListener(specialCharmListener);
    }
    return newGroups.toArray(new ILearningCharmGroup[newGroups.size()]);
  }

  private String getSubeffectParent(String subeffect) {
    return subeffect.split("\\.")[0] + "." + subeffect.split("\\.")[1];
  }

  private String getSubeffect(String subeffect) {
    return subeffect.split("\\.")[3];
  }

  @Override
  public ILearningCharmGroup[] getAllGroups() {
    List<ILearningCharmGroup> allGroups = new ArrayList<>();
    for (ILearningCharmGroup[] groups : nonMartialArtsGroupsByType.values()) {
      allGroups.addAll(Arrays.asList(groups));
    }
    allGroups.addAll(Arrays.asList(martialArtsGroups));
    return allGroups.toArray(new ILearningCharmGroup[allGroups.size()]);
  }

  @Override
  public Charm getCharmById(String charmId) {
    Charm charm = getCharmIdMap().getCharmById(charmId);
    if (charm != null) {
      return charm;
    }
    throw new IllegalArgumentException("No charm found for id \"" + charmId + "\"");
  }

  @Override
  public ILearningCharmGroup[] getCharmGroups(Identifier type) {
    if (MartialArtsUtilities.MARTIAL_ARTS.equals(type)) {
      return martialArtsGroups;
    }
    return Functions.forMap(nonMartialArtsGroupsByType, new ILearningCharmGroup[0]).apply(type);
  }

  private ILearningCharmGroup[] getMartialArtsGroups() {
    return getCharmGroups(MartialArtsUtilities.MARTIAL_ARTS);
  }

  @Override
  public Charm[] getLearnedCharms(boolean experienced) {
    List<Charm> allLearnedCharms = new ArrayList<>();
    for (ILearningCharmGroup group : getAllGroups()) {
      Collections.addAll(allLearnedCharms, group.getCreationLearnedCharms());
      if (experienced) {
        Collections.addAll(allLearnedCharms, group.getExperienceLearnedCharms());
      }
    }
    return allLearnedCharms.toArray(new Charm[allLearnedCharms.size()]);
  }

  @Override
  public CharmSpecialsModel getCharmSpecialsModel(Charm charm) {
    return manager.getSpecialCharmConfiguration(charm);
  }

  private void initLearnGroupForCharacterType(CharacterType type, GroupCharmTree charmTree) {
    ICharmGroup[] treeGroups = charmTree.getAllCharmGroups();
    ILearningCharmGroup[] groups = createGroups(treeGroups);
    nonMartialArtsGroupsByType.put(type, groups);
  }

  @Override
  public void unlearnAllAlienCharms() {
    for (ILearningCharmGroup[] groups : nonMartialArtsGroupsByType.values()) {
      for (ILearningCharmGroup group : groups) {
        if (nonMartialArtsOptions.isAlienType(group.getCharacterType())) {
          group.forgetAll();
        }
      }
    }
    for (ILearningCharmGroup group : martialArtsGroups) {
      group.unlearnExclusives();
    }
  }

  @Override
  public CharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    return nonMartialArtsOptions.getCharacterTypes(includeAlienTypes);
  }

  private void verifyCharms() {
    if (!hero.isFullyLoaded()) {
      return;
    }
    List<Charm> charmsToUnlearn = new ArrayList<>();
    for (Charm charm : this.getLearnedCharms(true)) {
      boolean prerequisitesForCharmAreNoLongerMet = !isLearnable(charm);
      boolean charmCanBeUnlearned = isUnlearnable(charm);
      if (prerequisitesForCharmAreNoLongerMet && charmCanBeUnlearned) {
        charmsToUnlearn.add(charm);
      }
    }
    for (Charm charm : charmsToUnlearn) {
      ILearningCharmGroup group = learningCharmGroupContainer.getLearningCharmGroup(charm);
      boolean learnedAtCreation = group.isLearned(charm, false);
      boolean learnedWithExperience = !learnedAtCreation;
      group.forgetCharm(charm, learnedWithExperience);
    }
  }

  @Override
  public void addLearnableListener(ChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public final boolean isLearnable(Charm charm) {
    if (isAlienCharm(charm)) {
      CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
      if (!(charmsRules.isAllowedAlienCharms(casteType))) {
        return false;
      }
      if (charm.hasAttribute(CharmAttributeList.NATIVE)) {
        return false;
      }
    }
    if (charm.isBlockedByAlternative(this)) {
      return false;
    }
    if (isMartialArts(charm)) {
      boolean isSiderealFormCharm = isFormMagic(charm) && hasLevel(Sidereal, charm);
      MartialArtsLearnModel martialArtsConfiguration = new MartialArtsLearnModelImpl(this, experience);
      if (isSiderealFormCharm && !martialArtsConfiguration.isAnyCelestialStyleCompleted()) {
        return false;
      }
      if (!charmsRules.getMartialArtsRules().isCharmAllowed(charm, isExperienced())) {
        return false;
      }
    }
    Charm[] learnedCharms = getLearnedCharms(true);
    for (IndirectCharmRequirement requirement : charm.getAttributeRequirements()) {
      if (!requirement.isFulfilled(learnedCharms)) {
        return false;
      }
    }
    if (!(new CharmTraitRequirementChecker(getPrerequisiteModifyingCharms(), traits, this).areTraitMinimumsSatisfied(charm))) {
      return false;
    }
    for (Charm parentCharm : charm.getLearnPrerequisitesCharms(this)) {
      if (!isLearnable(parentCharm)) {
        return false;
      }
    }
    return true;
  }

  private boolean isExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }

  private PrerequisiteModifyingCharms getPrerequisiteModifyingCharms() {
    if (prerequisiteModifyingCharms == null) {
      this.prerequisiteModifyingCharms = new PrerequisiteModifyingCharms(getSpecialCharms());
    }
    return prerequisiteModifyingCharms;
  }

  private boolean isLearnableWithoutPrerequisites(Charm charm) {
    if (!isLearnable(charm)) {
      return false;
    }
    for (Charm parentCharm : charm.getLearnPrerequisitesCharms(this)) {
      if (!isLearned(parentCharm)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isLearned(String charmId) {
    Charm charm = getCharmById(charmId);
    return charm != null && isLearned(charm);
  }

  public final boolean isUnlearnable(Charm charm) {
    ILearningCharmGroup group = getGroup(charm);
    return group.isUnlearnable(charm);
  }

  @Override
  public boolean isAlienCharm(Charm charm) {
    boolean isNotMartialArts = !isMartialArts(charm);
    boolean isOfAlienType = nonMartialArtsOptions.isAlienType(charm.getCharacterType());
    return isNotMartialArts && isOfAlienType;
  }

  @Override
  public CharmSpecialsModel getSpecialCharmConfiguration(String charmId) {
    Charm charm = getCharmById(charmId);
    return getCharmSpecialsModel(charm);
  }

  @Override
  public final boolean isCompulsiveCharm(Charm charm) {
    String[] compulsiveCharmIDs = getCompulsiveCharmIds();
    return ArrayUtils.contains(compulsiveCharmIDs, charm.getId());
  }

  @Override
  public final boolean isLearned(Charm charm) {
    ILearningCharmGroup group = getGroup(charm);
    return group != null && group.isLearned(charm);
  }

  private ILearningCharmGroup getGroupById(CharacterType characterType, String groupId) {
    List<ILearningCharmGroup> candidateGroups = new ArrayList<>();
    Collections.addAll(candidateGroups, getCharmGroups(characterType));
    Collections.addAll(candidateGroups, getMartialArtsGroups());
    for (ILearningCharmGroup group : candidateGroups) {
      if (group.getId().equals(groupId)) {
        return group;
      }
    }
    throw new IllegalArgumentException("No charm group defined for Id: " + groupId + "," + characterType);
  }

  @Override
  public final ILearningCharmGroup getGroup(Charm charm) {
    return getGroupById(charm.getCharacterType(), charm.getGroupId());
  }

  @Override
  public Charm[] getCharms(ICharmGroup charmGroup) {
    return nonMartialArtsOptions.getCharms(charmGroup);
  }

  private String[] getCompulsiveCharmIds() {
    // todo (sandra): compulsive charms
    return new String[0];
  }


  @Override
  public void addPrintProvider(PrintMagicProvider provider) {
    printMagicProviders.add(provider);
  }

  @Override
  public void addLearnProvider(MagicLearner provider) {
    magicLearners.add(provider);
  }

  @Override
  public MagicCreationCostEvaluator getMagicCostEvaluator() {
    return new MagicCreationCostEvaluator(magicLearners);
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    for (PrintMagicProvider provider : printMagicProviders) {
      provider.addPrintMagic(printMagic);
    }
  }

  public MartialArtsLevel getStandardMartialArtsLevel() {
    return charmsRules.getMartialArtsRules().getStandardLevel();
  }

  @Override
  public boolean isAlienCharmAllowed() {
    CasteType caste = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return charmsRules.isAllowedAlienCharms(caste);
  }
}