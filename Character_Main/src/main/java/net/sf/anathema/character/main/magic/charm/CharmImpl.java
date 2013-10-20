package net.sf.anathema.character.main.magic.charm;

import static net.sf.anathema.character.main.traits.types.AbilityType.MartialArts;

import java.util.ArrayList;
import java.util.Arrays;
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
import net.sf.anathema.character.main.magic.charm.prerequisite.DirectCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.SimpleCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.magic.parser.charms.CharmPrerequisiteList;
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
  private final List<CharmImpl> children = new ArrayList<>();
  private final List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
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
  
  public void extractParentCharms(Map<String, CharmImpl> charmsById) {
    prerequisites.addAll(Arrays.asList(prerequisisteList.getLearnPrerequisites()));
    for (CharmLearnPrerequisite prerequisite : prerequisites) {
    	prerequisite.link(charmsById);
    }
    List<DirectCharmLearnPrerequisite> directPrerequisites = getPrerequisitesOfType(DirectCharmLearnPrerequisite.class);
    for (DirectCharmLearnPrerequisite prerequisite : directPrerequisites) {
      Charm[] charms = prerequisite.getDirectPredecessors();
      for (Charm charm : charms) {
        ((CharmImpl)charm).addChild(this);
      }
    }
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
    for (DirectCharmLearnPrerequisite prerequisite : getPrerequisitesOfType(DirectCharmLearnPrerequisite.class)) {
    	prerequisiteCharms.addAll(Arrays.asList(prerequisite.getDirectPredecessors()));
    }
    return prerequisiteCharms;
  }

  @Override
  public Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    Set<Charm> prerequisiteCharms = new HashSet<>();
    for (DirectCharmLearnPrerequisite prerequisite : getPrerequisitesOfType(DirectCharmLearnPrerequisite.class)) {
    	prerequisiteCharms.addAll(Arrays.asList(prerequisite.getLearnPrerequisites(learnArbitrator)));
    }
    return prerequisiteCharms;
  }

  @Override
  public boolean isTreeRoot() {
    return getPrerequisitesOfType(DirectCharmLearnPrerequisite.class).isEmpty() &&
    	   getPrerequisitesOfType(IndirectCharmLearnPrerequisite.class).isEmpty();
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
	for (CharmLearnPrerequisite prerequisite : getPrerequisitesOfType(DirectCharmLearnPrerequisite.class)) {
		if (!prerequisite.isSatisfied(learnArbitrator)) {
			return false;
		}
	}
    return true;
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

  @SuppressWarnings("unused")
  @Override
  public <T extends CharmLearnPrerequisite> List<T> getPrerequisitesOfType(Class<T> clazz) {
	  List<T> matches = new ArrayList<>();
	  for (CharmLearnPrerequisite prerequisite : prerequisites) {
		  if (clazz.isInstance(prerequisite)) {
			  matches.add((T) prerequisite);
		  }
	  }
	  return matches;
  }

  public void addParentCharms(Charm... parent) {
    for (Charm charm : parent) {
    	prerequisites.add(new SimpleCharmLearnPrerequisite(charm));
    }
  }
}