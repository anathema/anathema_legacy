package net.sf.anathema.character.impl.model.charm;

import com.google.common.base.Functions;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.MartialArtsRules;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.charm.options.MartialArtsOptions;
import net.sf.anathema.character.impl.model.charm.special.DefaultMartialArtsCharmConfiguration;
import net.sf.anathema.character.impl.model.charm.special.SpecialCharmManager;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.presenter.magic.CharacterSourceBookFilter;
import net.sf.anathema.character.presenter.magic.EssenceLevelCharmFilter;
import net.sf.anathema.character.presenter.magic.ObtainableCharmFilter;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;
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

  private final ISpecialCharmManager manager;
  private final Map<Identified, ICharmTree> alienTreesByType = new HashMap<>();
  private final Map<Identified, ILearningCharmGroup[]> nonMartialArtsGroupsByType = new HashMap<>();
  private final Map<ICharacterType, ICharmTemplate> templatesByType = new HashMap<>();
  private final ICharacterType[] types;
  private final ILearningCharmGroupContainer learningCharmGroupContainer = new ILearningCharmGroupContainer() {
    @Override
    public ILearningCharmGroup getLearningCharmGroup(ICharm charm) {
      return getGroup(charm);
    }
  };
  private final CharacterTypes characterTypes;
  private ILearningCharmGroup[] martialArtsGroups;
  private final ICharacterModelContext context;
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final ICharmProvider provider;
  private final ICharmGroupArbitrator arbitrator;
  private List<ICharmFilter> filterSet = new ArrayList<>();
  private PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private MartialArtsOptions martialArtsOptions;

  public CharmConfiguration(IHealthConfiguration health, ICharacterModelContext context, CharacterTypes characterTypes, ITemplateRegistry registry,
                            ICharmProvider provider) {
    this.martialArtsOptions = new MartialArtsOptions(context, registry);
    this.manager = new SpecialCharmManager(this, health, context);
    this.context = context;
    this.provider = provider;
    this.characterTypes = characterTypes;
    List<ICharacterType> allCharacterTypes = new ArrayList<>();
    ICharmTemplate nativeCharmTemplate = getNativeCharmTemplate(registry);
    this.arbitrator = new LearningCharmGroupArbitrator(nativeCharmTemplate, context);
    this.martialArtsGroups = createGroups(martialArtsOptions.getAllCharmGroups());
    initCharacterType(nativeCharmTemplate, getNativeCharacterType());
    allCharacterTypes.add(getNativeCharacterType());
    initAlienTypes(registry, allCharacterTypes);
    initSpecialCharmConfigurations();
    this.types = allCharacterTypes.toArray(new ICharacterType[allCharacterTypes.size()]);
    filterSet.add(new ObtainableCharmFilter(this));
    filterSet.add(new CharacterSourceBookFilter(this));
    filterSet.add(new EssenceLevelCharmFilter());
  }

  private ICharmTemplate getNativeCharmTemplate(ITemplateRegistry registry) {
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    ITemplateType templateType = basicCharacterContext.getTemplateType();
    ICharacterTemplate template = registry.getTemplate(templateType);
    IMagicTemplate magicTemplate = template.getMagicTemplate();
    return magicTemplate.getCharmTemplate();
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    for (ILearningCharmGroup group : getAllGroups()) {
      group.addCharmLearnListener(listener);
    }
  }

  @Override
  public ICharmIdMap getCharmIdMap() {
    List<ICharmIdMap> trees = new ArrayList<ICharmIdMap>(alienTreesByType.values());
    trees.add(martialArtsOptions);
    ICharmIdMap[] treeArray = trees.toArray(new ICharmIdMap[trees.size()]);
    return new GroupedCharmIdMap(treeArray);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    return provider.getSpecialCharms(martialArtsOptions, getCharmIdMap(), getNativeCharacterType());
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
      ILearningCharmGroup group = new LearningCharmGroup(getLearnStrategy(), charmGroup, this, learningCharmGroupContainer, this);
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

  private ICharacterType getCharacterType(String charmId) {
    String characterType = charmId.substring(0, charmId.indexOf("."));
    for (ICharacterType type : types) {
      if (type.getId().equals(characterType)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Charm Id did not contain a valid character type");
  }

  @Override
  public String getCharmTrueName(String charmId) {
    return provider.getCharmRename(charmId);
  }

  @Override
  public ICharm getCharmById(String charmId) {
    charmId = getCharmTrueName(charmId);
    ICharm charm = martialArtsOptions.getCharmById(charmId);
    if (charm != null) {
      return charm;
    }
    ICharacterType characterType = getCharacterType(charmId);
    ICharmTree charmTree = alienTreesByType.get(characterType);
    charm = charmTree.getCharmById(charmId);
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
  public ILearningCharmGroup[] getCharmGroups(Identified type) {
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

  private void initCharacterType(ICharmTemplate charmTemplate, ICharacterType type) {
    CharmTree charmTree = new CharmTree(charmTemplate);
    alienTreesByType.put(type, charmTree);
    ILearningCharmGroup[] groups = createGroups(charmTree.getAllCharmGroups());
    nonMartialArtsGroupsByType.put(type, groups);
    templatesByType.put(type, charmTemplate);
  }

  private ICharmTemplate getCharmTemplate(ITemplateRegistry registry, ICharacterType type) {
    ICharacterTemplate defaultTemplate = registry.getDefaultTemplate(type);
    if (defaultTemplate == null) {
      return null;
    }
    return defaultTemplate.getMagicTemplate().getCharmTemplate();
  }

  private void initAlienTypes(ITemplateRegistry registry, List<ICharacterType> characterTypes) {
    for (ICharacterType type : this.characterTypes.findAll()) {
      if (characterTypes.contains(type)) {
        continue;
      }
      ICharmTemplate charmTemplate = getCharmTemplate(registry, type);
      if (charmTemplate != null && charmTemplate.canLearnCharms()) {
        initCharacterType(charmTemplate, type);
        characterTypes.add(type);
      }
    }
  }

  private ICharacterType getNativeCharacterType() {
    return context.getBasicCharacterContext().getCharacterType();
  }

  @Override
  public void unlearnAllAlienCharms() {
    for (ILearningCharmGroup[] groups : nonMartialArtsGroupsByType.values()) {
      for (ILearningCharmGroup group : groups) {
        if (isAlienType(group.getCharacterType())) {
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
    ICharacterType characterType;
    if (characterTypeId == null) {
      characterType = getNativeCharacterType();
    } else {
      characterType = characterTypes.findById(characterTypeId);
    }
    return getGroupById(characterType, groupName);
  }

  @Override
  public ICharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    if (!includeAlienTypes) {
      return new ICharacterType[]{getNativeCharacterType()};
    }
    return types;

  }

  public void initListening() {
    context.getCharacterListening().addChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void characterChanged() {
        verifyCharms();
        fireLearnConditionsChanged();
      }
    });
    addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(ICharm charm) {
        fireLearnConditionsChanged();
      }

      @Override
      public void charmLearned(ICharm charm) {
        fireLearnConditionsChanged();
      }
    });
  }

  private void verifyCharms() {
    if (!context.isFullyLoaded()) {
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

  private void fireLearnConditionsChanged() {
    control.announce().changeOccurred();
  }

  private ICharmLearnStrategy getLearnStrategy() {
    return context.getCharmContext().getCharmLearnStrategy();
  }

  @Override
  public final boolean isLearnable(ICharm charm) {
    if (isAlienCharm(charm)) {
      ICasteType casteType = context.getBasicCharacterContext().getCasteType();
      if (!getCharmTemplateForCharacterType().isAllowedAlienCharms(casteType)) {
        return false;
      }
      if (charm.hasAttribute(ICharmData.NATIVE)) {
        return false;
      }
    }
    if (charm.isBlockedByAlternative(context.getMagicCollection())) {
      return false;
    }
    if (isMartialArtsCharm(charm)) {
      boolean isSiderealFormCharm = isFormCharm(charm) && hasLevel(Sidereal, charm);
      MartialArtsCharmConfiguration martialArtsConfiguration =
              new DefaultMartialArtsCharmConfiguration(this, context.getMagicCollection(), context.getBasicCharacterContext());
      if (isSiderealFormCharm && !martialArtsConfiguration.isAnyCelestialStyleCompleted()) {
        return false;
      }
      if (!getMartialArtsRulesForCharacterType().isCharmAllowed(charm, martialArtsConfiguration, isExperienced())) {
        return false;
      }
    }
    ICharm[] learnedCharms = getLearnedCharms(true);
    for (IndirectCharmRequirement requirement : charm.getAttributeRequirements()) {
      if (!requirement.isFulfilled(learnedCharms)) {
        return false;
      }
    }
    if (!(new CharmTraitRequirementChecker(getPrerequisiteModifyingCharms(), context, this).areTraitMinimumsSatisfied(charm))) {
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
    return context.getBasicCharacterContext().isExperienced();
  }

  private MartialArtsRules getMartialArtsRulesForCharacterType() {
    return getCharmTemplateForCharacterType().getMartialArtsRules();
  }

  private ICharmTemplate getCharmTemplateForCharacterType() {
    return templatesByType.get(getNativeCharacterType());
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
    boolean isOfAlienType = isAlienType(charm.getCharacterType());
    return isNotMartialArts && isOfAlienType;
  }

  private boolean isAlienType(ICharacterType characterType) {
    ICharacterType nativeType = getNativeCharacterType();
    return !characterType.equals(nativeType);
  }

  @Override
  public ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId) {
    ICharm charm = getCharmById(charmId);
    return getSpecialCharmConfiguration(charm);
  }

  @Override
  public final boolean isCompulsiveCharm(ICharm charm) {
    String[] compulsiveCharmIDs = context.getAdditionalRules().getCompulsiveCharmIDs();
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
    return arbitrator.getCharms(charmGroup);
  }

  @Override
  public List<ICharmFilter> getCharmFilters() {
    return filterSet;
  }
}