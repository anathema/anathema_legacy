package net.sf.anathema.character.impl.model.charm;

import com.google.common.base.Functions;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.charm.options.MartialArtsOptions;
import net.sf.anathema.character.impl.model.charm.options.NonMartialArtsOptions;
import net.sf.anathema.character.impl.model.charm.special.DefaultMartialArtsCharmConfiguration;
import net.sf.anathema.character.impl.model.charm.special.SpecialCharmManager;
import net.sf.anathema.character.impl.model.context.magic.CreationCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ExperiencedCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ProxyCharmLearnStrategy;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.concept.CharacterConceptFetcher;
import net.sf.anathema.character.main.model.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.presenter.magic.CharacterSourceBookFilter;
import net.sf.anathema.character.presenter.magic.EssenceLevelCharmFilter;
import net.sf.anathema.character.presenter.magic.ObtainableCharmFilter;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.lang3.ArrayUtils;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.hasLevel;
import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.isFormCharm;
import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.isMartialArtsCharm;
import static net.sf.anathema.character.generic.magic.charms.MartialArtsLevel.Sidereal;

public class CharmConfiguration implements ICharmConfiguration {

  private final ProxyCharmLearnStrategy charmLearnStrategy = new ProxyCharmLearnStrategy(new CreationCharmLearnStrategy());
  private final ISpecialCharmManager manager;
  private final ILearningCharmGroupContainer learningCharmGroupContainer = new ILearningCharmGroupContainer() {
    @Override
    public ILearningCharmGroup getLearningCharmGroup(ICharm charm) {
      return getGroup(charm);
    }
  };
  private final ILearningCharmGroup[] martialArtsGroups;
  private final Map<Identifier, ILearningCharmGroup[]> nonMartialArtsGroupsByType = new HashMap<>();
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final ICharmProvider provider;
  private final ExperienceModel experience;
  private final TraitModel traits;
  private List<ICharmFilter> filterSet = new ArrayList<>();
  private PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private MartialArtsOptions martialArtsOptions;
  private NonMartialArtsOptions nonMartialArtsOptions;
  private Hero hero;
  private InitializationContext context;
  private CharmSpecialistImpl specialist;

  public CharmConfiguration(Hero hero, InitializationContext context, ICharacterModelContext characterContext) {
    this.specialist = new CharmSpecialistImpl(hero);
    this.experience = ExperienceModelFetcher.fetch(hero);
    this.traits = TraitModelFetcher.fetch(hero);
    this.experience.addStateChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        if (experience.isExperienced()) {
          charmLearnStrategy.setStrategy(new ExperiencedCharmLearnStrategy());
        } else {
          charmLearnStrategy.setStrategy(new CreationCharmLearnStrategy());
        }
      }
    });
    this.hero = hero;
    this.context = context;
    this.martialArtsOptions = new MartialArtsOptions(characterContext, context.getTemplateRegistry());
    this.nonMartialArtsOptions = new NonMartialArtsOptions(characterContext, context.getCharacterTypes(), context.getTemplateRegistry());
    this.manager = new SpecialCharmManager(specialist, hero, this, characterContext);
    this.provider = context.getCharmProvider();
    this.martialArtsGroups = createGroups(martialArtsOptions.getAllCharmGroups());
    initNonMartialArtsGroups();
    initSpecialCharmConfigurations();
    filterSet.add(new ObtainableCharmFilter(this));
    filterSet.add(new CharacterSourceBookFilter(this));
    filterSet.add(new EssenceLevelCharmFilter());
    addCompulsiveCharms(context.getTemplate());
    EssencePoolModelFetcher.fetch(hero).addOverdrivePool(new CharmOverdrivePool(this, experience));
    context.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        verifyCharms();
        control.announce().changeOccurred();
      }
    });
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
  }

  private void addCompulsiveCharms(HeroTemplate template) {
    String[] compulsiveCharms = template.getAdditionalRules().getCompulsiveCharmIDs();
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
  public ICharmIdMap getCharmIdMap() {
    List<ICharmIdMap> trees = new ArrayList<>();
    trees.add(nonMartialArtsOptions);
    trees.add(martialArtsOptions);
    return new GroupedCharmIdMap(trees);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    return provider.getSpecialCharms(martialArtsOptions, getCharmIdMap(), nonMartialArtsOptions.getNativeCharacterType());
  }

  private void initSpecialCharmConfigurations() {
    ICharmIdMap charmIdMap = getCharmIdMap();
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
              CharmConfiguration.this.getSpecialCharmConfiguration(mergedCharm) == null) {
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
  public void addLearnableListener(IChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public final boolean isLearnable(ICharm charm) {
    if (isAlienCharm(charm)) {
      ICasteType casteType = CharacterConceptFetcher.fetch(hero).getCaste().getType();
      if (!nonMartialArtsOptions.getNativeCharmTemplate().isAllowedAlienCharms(casteType)) {
        return false;
      }
      if (charm.hasAttribute(ICharmData.NATIVE)) {
        return false;
      }
    }
    IMagicCollection magicCollection = context.getMagicCollection();
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
    String[] compulsiveCharmIDs = context.getTemplate().getAdditionalRules().getCompulsiveCharmIDs();
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

  @Override
  public List<ICharmFilter> getCharmFilters() {
    return filterSet;
  }
}