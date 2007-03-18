package net.sf.anathema.character.impl.model.charm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ConfigurableCharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.magic.charm.MartialArtsCharmTree;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.IUnsupportedTemplate;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.charm.special.SpecialCharmManager;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.lib.collection.DefaultValueHashMap;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class CharmConfiguration implements ICharmConfiguration {

  private final ISpecialCharmManager manager;
  private final MartialArtsCharmTree martialArtsCharmTree;
  private final Map<ICharacterType, ICharmTree> alienTreesByType = new HashMap<ICharacterType, ICharmTree>();
  private final Map<ICharacterType, ILearningCharmGroup[]> nonMartialArtsGroupsByType = new DefaultValueHashMap<ICharacterType, ILearningCharmGroup[]>(
      new ILearningCharmGroup[0]);
  private final Map<ICharacterType, ICharmTemplate> templatesByType = new HashMap<ICharacterType, ICharmTemplate>();
  private final ICharacterType[] types;
  private final ILearningCharmGroupContainer learningCharmGroupContainer = new ILearningCharmGroupContainer() {
    public ILearningCharmGroup getLearningCharmGroup(ICharm charm) {
      return getGroup(charm);
    }
  };
  private ILearningCharmGroup[] martialArtsGroups;
  private final ICharacterModelContext context;
  private final ChangeControl control = new ChangeControl();
  private final ICharmProvider provider;
  private final ILearningCharmGroupArbitrator arbitrator;

  private final ICharmLearnListener learnableListener = new CharmLearnAdapter() {
    @Override
    public void charmForgotten(ICharm charm) {
      fireLearnConditionsChanged();
    }

    @Override
    public void charmLearned(ICharm charm) {
      fireLearnConditionsChanged();
    }
  };

  public CharmConfiguration(
      IHealthConfiguration health,
      ICharacterModelContext context,
      ITemplateRegistry registry,
      ICharmProvider provider) {
    this.manager = new SpecialCharmManager(this, health, context);
    this.context = context;
    this.provider = provider;
    IExaltedRuleSet rules = context.getBasicCharacterContext().getRuleSet();
    List<ICharacterType> allCharacterTypes = new ArrayList<ICharacterType>();
    ICharmTemplate nativeCharmTemplate = getNativeCharmTemplate(registry);
    this.arbitrator = new LearningCharmGroupArbitrator(nativeCharmTemplate, context.getBasicCharacterContext());
    this.martialArtsCharmTree = new MartialArtsCharmTree(nativeCharmTemplate, rules);
    this.martialArtsGroups = createGroups(martialArtsCharmTree.getAllCharmGroups());
    initCharacterType(nativeCharmTemplate, rules, getNativeCharacterType());
    allCharacterTypes.add(getNativeCharacterType());
    initAlienTypes(registry, rules, allCharacterTypes);
    initSpecialCharmConfigurations();
    types = allCharacterTypes.toArray(new ICharacterType[allCharacterTypes.size()]);
  }

  private ICharmTemplate getNativeCharmTemplate(ITemplateRegistry registry) {
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    return registry.getTemplate(
        basicCharacterContext.getTemplateType(),
        basicCharacterContext.getRuleSet().getEdition()).getMagicTemplate().getCharmTemplate();
  }

  public void addCharmLearnListener(ICharmLearnListener listener) {
    for (ILearningCharmGroup group : getAllGroups()) {
      group.addCharmLearnListener(listener);
    }
  }

  @Override
  public ICharmIdMap getCharmIdMap() {
    List<ICharmIdMap> trees = new ArrayList<ICharmIdMap>(alienTreesByType.values());
    trees.add(martialArtsCharmTree);
    ICharmTree[] treeArray = trees.toArray(new ICharmTree[trees.size()]);
    return new GroupedCharmIdMap(treeArray);
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    return provider.getSpecialCharms(
        context.getBasicCharacterContext().getRuleSet().getEdition(),
        new MartialArtsLearnableArbitrator(martialArtsCharmTree),
        getCharmIdMap(),
        getNativeCharacterType());
  }

  private void initSpecialCharmConfigurations() {
    ICharmIdMap charmIdMap = getCharmIdMap();
    for (ISpecialCharm specialCharm : getSpecialCharms()) {
      ICharm charm = charmIdMap.getCharmById(specialCharm.getCharmId());
      if (charm == null) {
        continue;
      }
      ILearningCharmGroup group = getGroupById(charm.getCharacterType(), charm.getGroupId());
      manager.registerSpecialCharmConfiguration(specialCharm, charm, group);
    }
  }

  public boolean contains(ICharm charm) {
    ICharacterType characterType = charm.getCharacterType();
    ICharm[] allCharms = alienTreesByType.get(characterType).getAllCharms();
    for (ICharm alienCharm : allCharms) {
      if (alienCharm == charm) {
        return true;
      }
    }
    return false;
  }

  private ILearningCharmGroup[] createGroups(ICharmGroup[] charmGroups) {
    List<ILearningCharmGroup> newGroups = new ArrayList<ILearningCharmGroup>();
    for (ICharmGroup charmGroup : charmGroups) {
      newGroups.add(new LearningCharmGroup(getLearnStrategy(), charmGroup, this, learningCharmGroupContainer, manager));
    }
    return newGroups.toArray(new LearningCharmGroup[0]);
  }

  private ICharm[] getAllCharms() {
    List<ICharm> charms = new ArrayList<ICharm>();
    for (ICharmTree tree : alienTreesByType.values()) {
      charms.addAll(Arrays.asList(tree.getAllCharms()));
    }
    return charms.toArray(new ICharm[charms.size()]);
  }

  public ILearningCharmGroup[] getAllGroups() {
    List<ILearningCharmGroup> allGroups = new ArrayList<ILearningCharmGroup>();
    for (ILearningCharmGroup[] groups : nonMartialArtsGroupsByType.values()) {
      allGroups.addAll(Arrays.asList(groups));
    }
    allGroups.addAll(Arrays.asList(martialArtsGroups));
    return allGroups.toArray(new ILearningCharmGroup[allGroups.size()]);
  }

  private ICharmTemplate getCharmTemplate(ICharacterType type) {
    return templatesByType.get(type);
  }

  private ICharacterType getCharacterType(String charmId) {
    String characterType = charmId.substring(0, charmId.indexOf(".")); //$NON-NLS-1$
    for (ICharacterType type : types) {
      if (type.getId().equals(characterType)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Charm Id did not contain a valid character type"); //$NON-NLS-1$
  }

  public ICharm getCharmById(String charmId) {
    ICharm charm = martialArtsCharmTree.getCharmById(charmId);
    if (charm != null) {
      return charm;
    }
    ICharacterType characterType = getCharacterType(charmId);
    charm = getCharmTree(characterType).getCharmById(charmId);
    if (charm != null) {
      return charm;
    }
    charm = CharmCache.getInstance().searchCharm(charmId, context.getBasicCharacterContext().getRuleSet());
    if (charm != null) {
      return charm;
    }
    throw new IllegalArgumentException("No charm for id \"" + charmId + "\""); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private ICharmIdMap getCharmTree(ICharacterType type) {
    return alienTreesByType.get(type);
  }

  public ICharm[] getCreationLearnedCharms() {
    List<ICharm> allLearnedCharms = new ArrayList<ICharm>();
    for (ILearningCharmGroup group : getAllGroups()) {
      Collections.addAll(allLearnedCharms, group.getCreationLearnedCharms());
    }
    return allLearnedCharms.toArray(new ICharm[allLearnedCharms.size()]);
  }

  public ICharm[] getExperienceLearnedCharms() {
    List<ICharm> allLearnedCharms = new ArrayList<ICharm>();
    for (ILearningCharmGroup group : getAllGroups()) {
      Collections.addAll(allLearnedCharms, group.getExperienceLearnedCharms());
    }
    return allLearnedCharms.toArray(new ICharm[0]);
  }

  @Override
  public ILearningCharmGroup[] getCharmGroups(IIdentificate type) {
    if (MartialArtsUtilities.MARTIAL_ARTS.equals(type)) {
      return martialArtsGroups;
    }
    return nonMartialArtsGroupsByType.get(type);
  }

  private ILearningCharmGroup[] getMartialArtsGroups() {
    return getCharmGroups(MartialArtsUtilities.MARTIAL_ARTS);
  }

  public ICharm[] getLearnedCharms(boolean experienced) {
    List<ICharm> allLearnedCharms = new ArrayList<ICharm>();
    for (ILearningCharmGroup group : getAllGroups()) {
      Collections.addAll(allLearnedCharms, group.getCreationLearnedCharms());
      if (experienced) {
        Collections.addAll(allLearnedCharms, group.getExperienceLearnedCharms());
      }
    }
    return allLearnedCharms.toArray(new ICharm[0]);
  }

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm) {
    return manager.getSpecialCharmConfiguration(charm);
  }

  private void initCharacterType(ICharmTemplate charmTemplate, IExaltedRuleSet rules, ICharacterType type) {
    CharmTree charmTree = new CharmTree(charmTemplate, rules);
    alienTreesByType.put(type, charmTree);
    ILearningCharmGroup[] groups = createGroups(charmTree.getAllCharmGroups());
    nonMartialArtsGroupsByType.put(type, groups);
    templatesByType.put(type, charmTemplate);
  }

  private ICharmTemplate getCharmTemplate(ITemplateRegistry registry, ICharacterType type) {
    ICharacterTemplate defaultTemplate = registry.getDefaultTemplate(type, context.getBasicCharacterContext()
        .getRuleSet()
        .getEdition());
    if (defaultTemplate == null || defaultTemplate instanceof IUnsupportedTemplate) {
      return null;
    }
    return defaultTemplate.getMagicTemplate().getCharmTemplate();
  }

  private void initAlienTypes(ITemplateRegistry registry, IExaltedRuleSet rules, List<ICharacterType> characterTypes) {
    for (ICharacterType type : CharacterType.values()) {
      if (characterTypes.contains(type)) {
        continue;
      }
      ICharmTemplate charmTemplate = getCharmTemplate(registry, type);
      if (charmTemplate != null && charmTemplate.knowsCharms(rules)) {
        initCharacterType(charmTemplate, rules, type);
        characterTypes.add(type);
      }
    }
  }

  private ICharacterType getNativeCharacterType() {
    return context.getBasicCharacterContext().getCharacterType();
  }

  public void unlearnAllAlienCharms() {
    for (ILearningCharmGroup[] groups : nonMartialArtsGroupsByType.values()) {
      for (ILearningCharmGroup group : groups) {
        if (group.getCharacterType() != getNativeCharacterType()) {
          group.forgetAll();
        }
      }
    }
    for (ILearningCharmGroup group : martialArtsGroups) {
      group.unlearnExclusives();
    }
  }

  public ILearningCharmGroup getGroup(String characterTypeId, String groupName) {
    final ICharacterType characterType = characterTypeId == null
        ? getNativeCharacterType()
        : CharacterType.getById(characterTypeId);
    return getGroupById(characterType, groupName);
  }

  public ICharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    if (!includeAlienTypes) {
      return new ICharacterType[] { getNativeCharacterType() };
    }
    return types;

  }

  public void initListening() {
    ConfigurableCharacterChangeListener changeListener = new ConfigurableCharacterChangeListener() {
      @Override
      public void configuredChangeOccured() {
        fireLearnConditionsChanged();
      }
    };
    changeListener.addTraitTypes(createPrerequisiteSet());
    context.getCharacterListening().addChangeListener(changeListener);
    addCharmLearnListener(learnableListener);
  }

  private ITraitType[] createPrerequisiteSet() {
    PrerequisiteSetBuilder prerequisiteSetBuilder = new PrerequisiteSetBuilder();
    prerequisiteSetBuilder.addCharms(getAllCharms());
    prerequisiteSetBuilder.addCharms(martialArtsCharmTree.getAllCharms());
    return prerequisiteSetBuilder.getAllPrerequisites().toArray(new ITraitType[0]);
  }

  public void addLearnableListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  private void fireLearnConditionsChanged() {
    control.fireChangedEvent();
  }

  private final ICharmLearnStrategy getLearnStrategy() {
    return context.getCharmContext().getCharmLearnStrategy();
  }

  public boolean isFullfilled(ICharmAttributeRequirement requirement) {
    int count = 0;
    for (ICharm charm : getLearnedCharms(true)) {
      if (charm.hasAttribute(requirement.getAttribute())) {
        count++;
      }
      if (count >= requirement.getCount()) {
        return true;
      }
    }
    return false;
  }

  public final boolean isLearnable(ICharm charm) {
    if (isAlienCharm(charm)) {
      ICasteType casteType = context.getBasicCharacterContext().getCasteType();
      if (!getCharmTemplate(getNativeCharacterType()).isAllowedAlienCharms(casteType)) {
        return false;
      }
      if (charm.hasAttribute(ICharmData.NOT_ALIEN_LEARNABLE)) {
        return false;
      }
    }
    if (charm.isBlockedByAlternative(context.getMagicCollection())) {
      return false;
    }
    if (MartialArtsUtilities.isMartialArtsCharm(charm)) {
      boolean isSiderealFormCharm = MartialArtsUtilities.isFormCharm(charm)
          && MartialArtsUtilities.hasLevel(MartialArtsLevel.Sidereal, charm);
      if (isSiderealFormCharm && !isCelestialMartialArtsGroupCompleted()) {
        return false;
      }
      if (!getCharmTemplate(getNativeCharacterType()).getMartialArtsRules().isCharmAllowed(
          charm,
          context.getCharmContext(),
          context.getBasicCharacterContext().isExperienced())) {
        return false;
      }
    }
    ICharmAttributeRequirement[] attributeRequirements = charm.getAttributeRequirements();
    for (ICharmAttributeRequirement requirement : attributeRequirements) {
      if (!isFullfilled(requirement)) {
        return false;
      }
    }
    IGenericTrait[] prerequisites = charm.getPrerequisites();
    for (IGenericTrait prerequisite : prerequisites) {
      IGenericTrait prerequisiteTrait = context.getTraitCollection().getTrait(prerequisite.getType());
      if (prerequisite.getCurrentValue() > prerequisiteTrait.getCurrentValue()) {
        return false;
      }
      IGenericTrait essenceTrait = context.getTraitCollection().getTrait(OtherTraitType.Essence);
      if (charm.getEssence().getCurrentValue() > essenceTrait.getCurrentValue()) {
        return false;
      }
    }
    for (ICharm parentCharm : charm.getLearnPrerequisitesCharms(this)) {
      if (!isLearnable(parentCharm)) {
        return false;
      }
    }
    return true;
  }

  public boolean isLearnable(String charmId) {
    ICharm charm = getCharmById(charmId);
    if (charm == null) {
      return false;
    }
    return isLearnable(charm);
  }

  public boolean isLearned(String charmId) {
    ICharm charm = getCharmById(charmId);
    if (charm == null) {
      return false;
    }
    return isLearned(charm);
  }

  public boolean isUnlearnable(String charmId) {
    ICharm charm = getCharmById(charmId);
    final ILearningCharmGroup group = getGroup(charm);
    return group.isUnlearnable(charm);
  }

  public boolean isAlienCharm(ICharm charm) {
    return !MartialArtsUtilities.isMartialArtsCharm(charm) && isAlienType(charm.getCharacterType());
  }

  private boolean isAlienType(ICharacterType characterType) {
    return characterType != getNativeCharacterType();
  }

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId) {
    ICharm charm = getCharmById(charmId);
    return getSpecialCharmConfiguration(charm);
  }

  public final boolean isCompulsiveCharm(ICharm charm) {
    String[] compulsiveCharmIDs = context.getAdditionalRules().getCompulsiveCharmIDs();
    return ArrayUtilities.contains(compulsiveCharmIDs, charm.getId());
  }

  public final boolean isLearned(ICharm charm) {
    ILearningCharmGroup group = getGroup(charm);
    return group != null && group.isLearned(charm);
  }

  private final ILearningCharmGroup getGroupById(ICharacterType characterType, String groupId) {
    List<ILearningCharmGroup> candidateGroups = new ArrayList<ILearningCharmGroup>();
    Collections.addAll(candidateGroups, getCharmGroups(characterType));
    Collections.addAll(candidateGroups, getMartialArtsGroups());
    for (ILearningCharmGroup group : candidateGroups) {
      if (group.getId().equals(groupId)) {
        return group;
      }
    }
    throw new IllegalArgumentException("No charm group defined for Id: " + groupId + "," + characterType); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public final ILearningCharmGroup getGroup(ICharm charm) {
    return getGroupById(charm.getCharacterType(), charm.getGroupId());
  }

  public ICharm[] getCharms(ICharmGroup charmGroup) {
    return arbitrator.getCharms(charmGroup);
  }

  public String[] getUncompletedCelestialMartialArtsGroups() {
    return arbitrator.getUncompletedCelestialMartialArtsGroups(getMartialArtsGroups());
  }

  public boolean isCelestialMartialArtsGroupCompleted() {
    return arbitrator.isCelestialMartialArtsGroupCompleted(getMartialArtsGroups());
  }
}