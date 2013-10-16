package net.sf.anathema.hero.dummy;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.SimpleCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.requirements.IndirectCharmRequirement;
import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.character.main.magic.charm.duration.SimpleDuration;
import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.character.main.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.combos.ComboRestrictions;
import net.sf.anathema.character.main.magic.charm.combos.IComboRestrictions;
import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.basic.attribute.MagicAttributeImpl;
import net.sf.anathema.character.main.magic.basic.cost.CostList;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DummyCharm extends SimpleIdentifier implements Charm {

  private Duration duration;
  private IComboRestrictions comboRestrictions = new ComboRestrictions();
  private ValuedTraitType[] prerequisites;
  private List<CharmLearnPrerequisite> learnPrerequisites = new ArrayList<>();
  private Set<Charm> parentCharms;
  private Set<Charm> learnFollowUpCharms = new HashSet<>();
  private List<IndirectCharmRequirement> requirements = new ArrayList<>();
  private CharacterType characterType;
  private String groupId;
  private CharmTypeModel model = new CharmTypeModel();
  public List<MagicAttribute> attributes = new ArrayList<>();

  public void setGeneric(boolean generic) {
    isGeneric = generic;
  }

  private boolean isGeneric = false;

  public DummyCharm(String duration, CharmType charmType, IComboRestrictions comboRestrictions, ValuedTraitType[] prerequisites) {
    super("DummyCharmDefaultId");
    this.prerequisites = prerequisites;
    this.duration = SimpleDuration.getDuration(duration);
    this.comboRestrictions = comboRestrictions;
    this.model.setCharmType(charmType);
  }

  public DummyCharm() {
    this(null);
  }

  public DummyCharm(String id) {
    this(id, new Charm[0]);
  }

  public DummyCharm(String id, Charm... parents) {
    this(id, parents, new ValuedTraitType[0]);
  }

  public DummyCharm(String id, Charm[] parents, ValuedTraitType[] prerequisites) {
    super(id);
    this.parentCharms = new LinkedHashSet<>();
    Collections.addAll(parentCharms, parents);
    for (Charm charm : parents) {
    	learnPrerequisites.add(new SimpleCharmLearnPrerequisite(charm));
    }
    this.prerequisites = prerequisites;
  }

  @SuppressWarnings("UnusedDeclaration")
  public void addAttributeRequirement(IndirectCharmRequirement requirement) {
    requirements.add(requirement);
  }

  public void addLearnFollowUpCharm(Charm charm) {
    learnFollowUpCharms.add(charm);
  }

  @Override
  public IndirectCharmRequirement[] getAttributeRequirements() {
    return requirements.toArray(new IndirectCharmRequirement[requirements.size()]);
  }

  @Override
  public CharacterType getCharacterType() {
    return characterType;
  }

  public void setCharacterType(CharacterType type) {
    characterType = type;
  }

  @Override
  public boolean isInstanceOfGenericCharm() {
    return isGeneric;
  }

  @Override
  public IComboRestrictions getComboRules() {
    return comboRestrictions;
  }

  @Override
  public Duration getDuration() {
    return duration;
  }

  @Override
  public ValuedTraitType getEssence() {
    return null;
  }

  @Override
  public String getGroupId() {
    return groupId != null ? groupId : prerequisites[0].getType().getId();
  }

  @Override
  public Set<Charm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator) {
    return learnFollowUpCharms;
  }

  @Override
  public Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    return parentCharms;
  }

  @Override
  public Set<Charm> getLearnChildCharms() {
    return learnFollowUpCharms;
  }
  
  @Override
  public List<CharmLearnPrerequisite> getLearnPrerequisites() {
  	return learnPrerequisites;
  }

  @Override
  public Set<Charm> getParentCharms() {
    return parentCharms;
  }

  @Override
  public ValuedTraitType[] getPrerequisites() {
    return prerequisites;
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return prerequisites[0].getType();
  }

  @Override
  public Set<Charm> getRenderingPrerequisiteCharms() {
    return parentCharms;
  }

  @Override
  public Set<IndirectCharmRequirement> getIndirectRequirements() {
    HashSet<IndirectCharmRequirement> charmRequirements = new HashSet<>();
    Collections.addAll(charmRequirements, getAttributeRequirements());
    return charmRequirements;
  }

  @Override
  public CostList getTemporaryCost() {
    return null;
  }

  @Override
  public boolean hasAttribute(Identifier attribute) {
    for (MagicAttribute magicAttribute : attributes) {
      if (magicAttribute.getId().equals(attribute.getId())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getAttributeValue(Identifier attribute) {
    return null;
  }

  @Override
  public boolean isBlockedByAlternative(ICharmLearnArbitrator learnArbitrator) {
    return false;
  }

  @Override
  public Set<Charm> getMergedCharms() {
    return new HashSet<>();
  }

  @Override
  public boolean isFavored(Hero hero) {
    if (prerequisites.length <= 0) {
      return false;
    }
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    ValuedTraitType trait = traitMap.getTrait(getPrimaryTraitType());
    return trait.isCasteOrFavored();
  }

  @Override
  public boolean isTreeRoot() {
    return parentCharms.size() == 0;
  }

  @Override
  public String toString() {
    return getId();
  }

  public void setGroupId(String expectedGroup) {
    this.groupId = expectedGroup;
  }

  @Override
  public SourceBook[] getSources() {
    return new SourceBook[]{null};
  }

  @Override
  public SourceBook getPrimarySource() {
    return null;
  }

  public void setCharmType(CharmType type) {
    model.setCharmType(type);
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public void setPrerequisites(net.sf.anathema.character.main.traits.types.ValuedTraitType[] prerequisites) {
    this.prerequisites = prerequisites;
  }

  @Override
  public CharmTypeModel getCharmTypeModel() {
    return model;
  }

  @Override
  public MagicAttribute[] getAttributes() {
    return attributes.toArray(new MagicAttribute[attributes.size()]);
  }

  public void addKeyword(MagicAttributeImpl attribute) {
    this.attributes.add(attribute);
  }
}