package net.sf.anathema.character.main.magic.charm;

import static net.sf.anathema.character.main.traits.types.AbilityType.MartialArts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.character.main.magic.basic.AbstractMagic;
import net.sf.anathema.character.main.magic.basic.attribute.MagicAttributeImpl;
import net.sf.anathema.character.main.magic.basic.cost.ICostList;
import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.character.main.magic.charm.combos.IComboRestrictions;
import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.SimpleCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.requirements.GroupedCharmRequirement;
import net.sf.anathema.character.main.magic.charm.requirements.IndirectCharmRequirement;
import net.sf.anathema.character.main.magic.charm.requirements.SelectiveCharmGroup;
import net.sf.anathema.character.main.magic.charm.requirements.SelectiveCharmGroups;
import net.sf.anathema.character.main.magic.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.character.main.magic.parser.charms.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.util.SimpleIdentifier;

import com.google.common.base.Preconditions;

public class CharmImpl extends AbstractMagic implements Charm {

  private final CharmPrerequisiteList prerequisisteList;

  private final CharacterType characterType;
  private final IComboRestrictions comboRules;
  private final Duration duration;
  private final String group;
  private final boolean isGeneric;

  private final SourceBook[] sources;
  private final ICostList temporaryCost;

  private final List<Set<Charm>> alternatives = new ArrayList<>();
  private final List<Set<Charm>> merges = new ArrayList<>();
  private final List<Charm> parentCharms = new ArrayList<>();
  private final List<CharmImpl> children = new ArrayList<>();
  private final List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
  private final SelectiveCharmGroups selectiveCharmGroups = new SelectiveCharmGroups();
  private final Set<String> favoredCasteIds = new HashSet<>();

  private final ICharmTypeModel typeModel;

  public CharmImpl(CharacterType characterType, String id, String group, boolean isGeneric, CharmPrerequisiteList prerequisiteList,
                   ICostList temporaryCost, IComboRestrictions comboRules, Duration duration, ICharmTypeModel charmTypeModel,
                   SourceBook[] sources) {
    super(id);
    Preconditions.checkNotNull(prerequisiteList);
    Preconditions.checkNotNull(characterType);
    Preconditions.checkNotNull(id);
    Preconditions.checkNotNull(group);
    Preconditions.checkNotNull(temporaryCost);
    Preconditions.checkNotNull(comboRules);
    Preconditions.checkNotNull(duration);
    Preconditions.checkNotNull(charmTypeModel.getCharmType());
    Preconditions.checkNotNull(sources);
    this.characterType = characterType;
    this.group = group;
    this.isGeneric = isGeneric;
    this.prerequisisteList = prerequisiteList;
    this.temporaryCost = temporaryCost;
    this.comboRules = comboRules;
    this.duration = duration;
    this.typeModel = charmTypeModel;
    this.sources = sources;
    for (SelectiveCharmGroupTemplate template : prerequisiteList.getSelectiveCharmGroups()) {
      selectiveCharmGroups.add(new SelectiveCharmGroup(template));
    }
  }

  @Override
  public ICharmTypeModel getCharmTypeModel() {
    return typeModel;
  }

  @Override
  public CharacterType getCharacterType() {
    return characterType;
  }

  @Override
  public Duration getDuration() {
    return duration;
  }

  @Override
  public ValuedTraitType getEssence() {
    return prerequisisteList.getEssence();
  }

  @Override
  public ValuedTraitType[] getPrerequisites() {
    return prerequisisteList.getPrerequisites();
  }

  @Override
  public SourceBook[] getSources() {
    return sources.length > 0 ? sources : null;
  }

  @Override
  public SourceBook getPrimarySource() {
    return sources.length > 0 ? sources[0] : null;
  }

  @Override
  public ICostList getTemporaryCost() {
    return temporaryCost;
  }

  @Override
  public String getGroupId() {
    return group;
  }

  @Override
  public boolean isInstanceOfGenericCharm() {
    return isGeneric;
  }

  @Override
  public IComboRestrictions getComboRules() {
    return comboRules;
  }

  public void addAlternative(Set<Charm> alternative) {
    alternatives.add(alternative);
  }

  @Override
  public boolean isBlockedByAlternative(ICharmLearnArbitrator learnArbitrator) {
    for (Set<Charm> alternative : alternatives) {
      for (Charm charm : alternative) {
        boolean isThis = charm.getId().equals(getId());
        if (!isThis && learnArbitrator.isLearned(charm)) {
          return true;
        }
      }
    }
    return false;
  }

  public void addMerged(Set<Charm> merged) {
    if (!merged.isEmpty()) {
      merges.add(merged);
      if (!hasAttribute(CharmAttributeList.MERGED_ATTRIBUTE)) {
        addMagicAttribute(new MagicAttributeImpl(CharmAttributeList.MERGED_ATTRIBUTE.getId(), true));
      }
    }
  }

  @Override
  public Set<Charm> getMergedCharms() {
    Set<Charm> mergedCharms = new HashSet<>();
    for (Set<Charm> merge : merges) {
      mergedCharms.addAll(merge);
    }
    return mergedCharms;
  }

  @Override
  public Set<Charm> getParentCharms() {
    return new HashSet<>(parentCharms);
  }
  
  public void extractParentCharms(Map<String, CharmImpl> charmsById) {
    if (parentCharms.size() > 0) {
      return;
    }
    for (String parentId : prerequisisteList.getParentIDs()) {
      CharmImpl parentCharm = charmsById.get(parentId);
      Preconditions.checkNotNull(parentCharm, "Parent Charm " + parentId + " not defined for " + getId());
      parentCharms.add(parentCharm);
      parentCharm.addChild(this);
    }
    for (SelectiveCharmGroup charmGroup : selectiveCharmGroups) {
      charmGroup.extractCharms(charmsById, this);
    }
    
    prerequisites.addAll(Arrays.asList(new CharmLearnPrerequisiteBuilder(prerequisisteList,
    		parentCharms.toArray(new Charm[0]),
    		selectiveCharmGroups.getOpenGroups().toArray(new SelectiveCharmGroup[0]),
    		selectiveCharmGroups.getCombinedGroups().toArray(new SelectiveCharmGroup[0])).getPrerequisites()));
  }
  
  @Override
  public List<CharmLearnPrerequisite> getLearnPrerequisites() {
	  return prerequisites;
  }

  public void addChild(CharmImpl child) {
    children.add(child);
  }

  @Override
  public Set<Charm> getRenderingPrerequisiteCharms() {
    Set<Charm> prerequisiteCharms = new HashSet<>();
    prerequisiteCharms.addAll(parentCharms);
    for (SelectiveCharmGroup charmGroup : selectiveCharmGroups.getOpenGroups()) {
      prerequisiteCharms.addAll(Arrays.asList(charmGroup.getAllGroupCharms()));
    }
    return prerequisiteCharms;
  }

  @Override
  public Set<IndirectCharmRequirement> getIndirectRequirements() {
    Set<IndirectCharmRequirement> indirectRequirements = new HashSet<>();
    for (SelectiveCharmGroup charmGroup : selectiveCharmGroups.getCombinedGroups()) {
      GroupedCharmRequirement requirement = new GroupedCharmRequirement(charmGroup);
      indirectRequirements.add(requirement);
    }
    Collections.addAll(indirectRequirements, getAttributeRequirements());
    return indirectRequirements;
  }

  @Override
  public Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    Set<Charm> prerequisiteCharms = new HashSet<>();
    for (Charm charm : getParentCharms()) {
      prerequisiteCharms.addAll(charm.getLearnPrerequisitesCharms(learnArbitrator));
      prerequisiteCharms.add(charm);
    }
    for (SelectiveCharmGroup charmGroup : selectiveCharmGroups) {
      prerequisiteCharms.addAll(charmGroup.getLearnPrerequisitesCharms(learnArbitrator));
    }
    return prerequisiteCharms;
  }

  @Override
  public boolean isTreeRoot() {
    return parentCharms.size() == 0 && selectiveCharmGroups.isEmpty() && getAttributeRequirements().length == 0;
  }

  @Override
  public Set<Charm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator) {
    CompositeLearnWorker learnWorker = new CompositeLearnWorker(learnArbitrator);
    for (CharmImpl child : children) {
      child.addCharmsToForget(learnWorker);
    }
    return learnWorker.getForgottenCharms();
  }

  @Override
  public Set<Charm> getLearnChildCharms() {
    return new HashSet<Charm>(children);
  }

  private void addCharmsToForget(ICharmLearnWorker learnWorker) {
    if (isCharmPrerequisiteListFullfilled(learnWorker)) {
      return;
    }
    learnWorker.forget(this);
    for (CharmImpl child : children) {
      child.addCharmsToForget(learnWorker);
    }
  }

  private boolean isCharmPrerequisiteListFullfilled(ICharmLearnArbitrator learnArbitrator) {
    for (Charm parent : parentCharms) {
      if (!learnArbitrator.isLearned(parent)) {
        return false;
      }
    }
    for (SelectiveCharmGroup selectiveGroup : selectiveCharmGroups) {
      if (!selectiveGroup.holdsThreshold(learnArbitrator)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public IndirectCharmRequirement[] getAttributeRequirements() {
    return prerequisisteList.getAttributeRequirements();
  }

  public void addFavoredCasteId(String casteId) {
    favoredCasteIds.add(casteId);
  }

  @Override
  public boolean isFavored(Hero hero) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    boolean specialFavored = favoredCasteIds.contains(concept.getCaste().getType().getId());
    if (specialFavored) {
      return true;
    }
    if (getPrerequisites().length <= 0) {
      return false;
    }
    TraitType primaryTraitType = getPrimaryTraitType();
    if (hasAttribute(new SimpleIdentifier("MartialArts")) && traitMap.getTrait(MartialArts).isCasteOrFavored()) {
      return true;
    }
    ValuedTraitType trait = traitMap.getTrait(primaryTraitType);
    return trait.isCasteOrFavored();
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return getPrerequisites().length == 0 ? OtherTraitType.Essence : getPrerequisites()[0].getType();
  }

  public void addParentCharms(Charm... parent) {
    parentCharms.addAll(Arrays.asList(parent));
    for (Charm charm : parent) {
    	prerequisites.add(new SimpleCharmLearnPrerequisite(charm));
    }
  }
}