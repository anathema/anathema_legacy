package net.sf.anathema.hero.magic.model.charms;

import com.google.common.base.Functions;
import net.sf.anathema.character.main.magic.display.view.charms.CharacterChangeCharmListener;
import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.magic.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.main.magic.model.charm.CharmSpecialistImpl;
import net.sf.anathema.character.main.magic.model.charmtree.CharmTraitRequirementChecker;
import net.sf.anathema.character.main.magic.model.charm.GroupedCharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnListener;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroupContainer;
import net.sf.anathema.character.main.magic.model.charm.ISpecialCharmManager;
import net.sf.anathema.character.main.magic.model.charms.LearningCharmGroup;
import net.sf.anathema.character.main.magic.model.charm.PrerequisiteModifyingCharms;
import net.sf.anathema.character.main.magic.model.charms.options.MartialArtsOptions;
import net.sf.anathema.character.main.magic.model.charms.options.NonMartialArtsOptions;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.ICharmData;
import net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charmtree.GroupCharmTree;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.magic.ICharmProvider;
import net.sf.anathema.character.main.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.magic.model.charms.context.CreationCharmLearnStrategy;
import net.sf.anathema.hero.magic.model.charms.context.ExperiencedCharmLearnStrategy;
import net.sf.anathema.hero.magic.model.charms.context.ProxyCharmLearnStrategy;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.magic.MagicCollection;
import net.sf.anathema.hero.magic.MagicCollectionImpl;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
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

import static net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever.getNativeTemplate;
import static net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities.hasLevel;
import static net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities.isFormCharm;
import static net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities.isMartialArtsCharm;
import static net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel.Sidereal;

public class CharmsModelImpl implements CharmsModel {

  private final ProxyCharmLearnStrategy charmLearnStrategy = new ProxyCharmLearnStrategy(new CreationCharmLearnStrategy());
  private ISpecialCharmManager manager;
  private ILearningCharmGroupContainer learningCharmGroupContainer = new ILearningCharmGroupContainer() {
    @Override
    public ILearningCharmGroup getLearningCharmGroup(ICharm charm) {
      return getGroup(charm);
    }
  };
  private ILearningCharmGroup[] martialArtsGroups;
  private final Map<Identifier, ILearningCharmGroup[]> nonMartialArtsGroupsByType = new HashMap<>();
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private ICharmProvider provider;
  private ExperienceModel experience;
  private TraitModel traits;
  private PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private MartialArtsOptions martialArtsOptions;
  private NonMartialArtsOptions nonMartialArtsOptions;
  private Hero hero;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    CharmSpecialistImpl specialist = new CharmSpecialistImpl(hero);
    this.experience = ExperienceModelFetcher.fetch(hero);
    this.traits = TraitModelFetcher.fetch(hero);
    this.hero = hero;
    this.martialArtsOptions = new MartialArtsOptions(hero);
    this.nonMartialArtsOptions = new NonMartialArtsOptions(hero, context.getCharacterTypes(), context.getCharmTemplateRetriever());
    this.manager = new SpecialCharmManager(specialist, hero, this);
    this.provider = context.getCharmProvider();
    this.martialArtsGroups = createGroups(martialArtsOptions.getAllCharmGroups());
    initNonMartialArtsGroups();
    initSpecialCharmConfigurations();
    addCompulsiveCharms(hero.getTemplate());
    EssencePoolModelFetcher.fetch(hero).addOverdrivePool(new CharmOverdrivePool(this, experience));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (ILearningCharmGroup group : getAllGroups()) {
      group.addCharmLearnListener(new CharmLearnAdapter() {
        @Override
        public void charmForgotten(ICharm charm) {
          control.announce().changeOccurred();
        }

        @Override
        public void charmLearned(ICharm charm) {
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

  private void addCompulsiveCharms(HeroTemplate template) {
    String[] compulsiveCharms = getCompulsiveCharmIds();

    for (String charmId : compulsiveCharms) {
      ICharm charm = getCharmById(charmId);
      getGroup(charm).learnCharm(charm, false);
    }
  }

  private void initNonMartialArtsGroups() {
    Iterable<ICharacterType> availableCharacterTypes = nonMartialArtsOptions.getAvailableCharacterTypes();
    for (ICharacterType characterType : availableCharacterTypes) {
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
      ICharm charm = charmIdMap.getCharmById(specialCharm.getCharmId());
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
      public void charmLearned(ICharm charm) {
        learnOtherCharmsFromMerge(charm);
        learnDirectChildrenActivatedViaThereMerge(charm);
      }

      private void learnDirectChildrenActivatedViaThereMerge(ICharm charm) {
        for (ICharm child : charm.getLearnChildCharms()) {
          boolean learnedMerged = false;
          for (ICharm mergedCharm : child.getMergedCharms()) {
            learnedMerged = learnedMerged || isLearned(mergedCharm);
          }
          if (learnedMerged && isLearnable(child)) {
            getGroup(child).learnCharm(child, isExperienced());
          }
        }
      }

      private void learnOtherCharmsFromMerge(ICharm charm) {
        for (ICharm mergedCharm : charm.getMergedCharms()) {
          if (!isLearned(mergedCharm) && isLearnableWithoutPrerequisites(mergedCharm) &&
                  CharmsModelImpl.this.getSpecialCharmConfiguration(mergedCharm) == null) {
            getGroup(mergedCharm).learnCharm(mergedCharm, isExperienced());
          }
        }
      }

      @Override
      public void charmForgotten(ICharm charm) {
        for (ICharm mergedCharm : charm.getMergedCharms()) {
          if (isLearned(mergedCharm)) {
            getGroup(mergedCharm).forgetCharm(mergedCharm, isExperienced());
          }
        }
      }
    };
    ICharmLearnListener specialCharmListener = new CharmLearnAdapter() {

      @Override
      public void recalculateRequested() {
        for (ICharm charm : getLearnedCharms(true)) {
          boolean prereqsMet = true;
          for (ICharm parent : charm.getParentCharms()) {
            for (String subeffectRequirement : charm.getParentSubeffects()) {
              if (getSubeffectParent(subeffectRequirement).equals(parent.getId())) {
                ISpecialCharmConfiguration config = getSpecialCharmConfiguration(getSubeffectParent(subeffectRequirement));
                if (config instanceof IMultipleEffectCharmConfiguration) {
                  IMultipleEffectCharmConfiguration mConfig = (IMultipleEffectCharmConfiguration) config;
                  prereqsMet = prereqsMet && mConfig.getEffectById(getSubeffect(subeffectRequirement)).isLearned();
                }
                if (config instanceof IMultiLearnableCharmConfiguration) {
                  IMultiLearnableCharmConfiguration mConfig = (IMultiLearnableCharmConfiguration) config;
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
  public String getCharmTrueName(String charmId) {
    return provider.getCharmRename(charmId);
  }

  @Override
  public ICharm getCharmById(String charmId) {
    String trueCharmId = getCharmTrueName(charmId);
    ICharm charm = getCharmIdMap().getCharmById(trueCharmId);
    if (charm != null) {
      return charm;
    }
    throw new IllegalArgumentException("No charm found for id \"" + charmId + "\"");
  }

  @Override
  public ICharm[] getCreationLearnedCharms() {
    List<ICharm> allLearnedCharms = new ArrayList<>();
    for (ILearningCharmGroup group : getAllGroups()) {
      Collections.addAll(allLearnedCharms, group.getCreationLearnedCharms());
    }
    return allLearnedCharms.toArray(new ICharm[allLearnedCharms.size()]);
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
  public ICharm[] getLearnedCharms(boolean experienced) {
    List<ICharm> allLearnedCharms = new ArrayList<>();
    for (ILearningCharmGroup group : getAllGroups()) {
      Collections.addAll(allLearnedCharms, group.getCreationLearnedCharms());
      if (experienced) {
        Collections.addAll(allLearnedCharms, group.getExperienceLearnedCharms());
      }
    }
    return allLearnedCharms.toArray(new ICharm[allLearnedCharms.size()]);
  }

  @Override
  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm) {
    return manager.getSpecialCharmConfiguration(charm);
  }

  private void initLearnGroupForCharacterType(ICharacterType type, GroupCharmTree charmTree) {
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
  public ILearningCharmGroup getGroup(String characterTypeId, String groupName) {
    ICharacterType characterType = nonMartialArtsOptions.getCharacterType(characterTypeId);
    return getGroupById(characterType, groupName);
  }

  @Override
  public ICharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    return nonMartialArtsOptions.getCharacterTypes(includeAlienTypes);

  }

  private void verifyCharms() {
    if (!hero.isFullyLoaded()) {
      return;
    }
    List<ICharm> charmsToUnlearn = new ArrayList<>();
    for (ICharm charm : this.getLearnedCharms(true)) {
      boolean prerequisitesForCharmAreNoLongerMet = !isLearnable(charm);
      boolean charmCanBeUnlearned = isUnlearnable(charm);
      if (prerequisitesForCharmAreNoLongerMet && charmCanBeUnlearned) {
        charmsToUnlearn.add(charm);
      }
    }
    for (ICharm charm : charmsToUnlearn) {
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
  public final boolean isLearnable(ICharm charm) {
    if (isAlienCharm(charm)) {
      CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
      if (!(getNativeTemplate(hero).isAllowedAlienCharms(casteType))) {
        return false;
      }
      if (charm.hasAttribute(ICharmData.NATIVE)) {
        return false;
      }
    }
    MagicCollection magicCollection = new MagicCollectionImpl(hero);
    if (charm.isBlockedByAlternative(magicCollection)) {
      return false;
    }
    if (isMartialArtsCharm(charm)) {
      boolean isSiderealFormCharm = isFormCharm(charm) && hasLevel(Sidereal, charm);
      MartialArtsCharmConfiguration martialArtsConfiguration = new DefaultMartialArtsCharmConfiguration(this, magicCollection, experience);
      if (isSiderealFormCharm && !martialArtsConfiguration.isAnyCelestialStyleCompleted()) {
        return false;
      }
      if (!martialArtsOptions.getMartialArtsRulesForCharacterType().isCharmAllowed(charm, martialArtsConfiguration, isExperienced())) {
        return false;
      }
    }
    ICharm[] learnedCharms = getLearnedCharms(true);
    for (IndirectCharmRequirement requirement : charm.getAttributeRequirements()) {
      if (!requirement.isFulfilled(learnedCharms)) {
        return false;
      }
    }
    if (!(new CharmTraitRequirementChecker(getPrerequisiteModifyingCharms(), traits, this).areTraitMinimumsSatisfied(charm))) {
      return false;
    }
    for (ICharm parentCharm : charm.getLearnPrerequisitesCharms(this)) {
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

  private boolean isLearnableWithoutPrerequisites(ICharm charm) {
    if (!isLearnable(charm)) {
      return false;
    }
    for (ICharm parentCharm : charm.getLearnPrerequisitesCharms(this)) {
      if (!isLearned(parentCharm)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isLearned(String charmId) {
    ICharm charm = getCharmById(charmId);
    return charm != null && isLearned(charm);
  }

  public final boolean isUnlearnable(ICharm charm) {
    ILearningCharmGroup group = getGroup(charm);
    return group.isUnlearnable(charm);
  }

  @Override
  public boolean isAlienCharm(ICharm charm) {
    boolean isNotMartialArts = !isMartialArtsCharm(charm);
    boolean isOfAlienType = nonMartialArtsOptions.isAlienType(charm.getCharacterType());
    return isNotMartialArts && isOfAlienType;
  }

  @Override
  public ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId) {
    ICharm charm = getCharmById(charmId);
    return getSpecialCharmConfiguration(charm);
  }

  @Override
  public final boolean isCompulsiveCharm(ICharm charm) {
    String[] compulsiveCharmIDs = getCompulsiveCharmIds();
    return ArrayUtils.contains(compulsiveCharmIDs, charm.getId());
  }

  @Override
  public final boolean isLearned(ICharm charm) {
    ILearningCharmGroup group = getGroup(charm);
    return group != null && group.isLearned(charm);
  }

  private ILearningCharmGroup getGroupById(ICharacterType characterType, String groupId) {
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
  public final ILearningCharmGroup getGroup(ICharm charm) {
    return getGroupById(charm.getCharacterType(), charm.getGroupId());
  }

  @Override
  public ICharm[] getCharms(ICharmGroup charmGroup) {
    return nonMartialArtsOptions.getCharms(charmGroup);
  }

  private String[] getCompulsiveCharmIds() {
    // todo (sandra): compulsive charms
    return new String[0];
  }
}