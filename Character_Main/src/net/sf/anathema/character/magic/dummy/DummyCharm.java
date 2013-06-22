package net.sf.anathema.character.magic.dummy;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DummyCharm extends SimpleIdentifier implements ICharm {

  private IDuration duration;
  private IComboRestrictions comboRestrictions = new ComboRestrictions();
  private GenericTrait[] prerequisites;
  private Set<ICharm> parentCharms;
  private Set<ICharm> learnFollowUpCharms = new HashSet<>();
  private List<IndirectCharmRequirement> requirements = new ArrayList<>();
  private ICharacterType characterType;
  private String groupId;
  private CharmTypeModel model = new CharmTypeModel();
  private List<ICharmAttribute> attributes = new ArrayList<>();

  public void setGeneric(boolean generic) {
    isGeneric = generic;
  }

  private boolean isGeneric = false;

  public DummyCharm(String duration, CharmType charmType, IComboRestrictions comboRestrictions, GenericTrait[] prerequisites) {
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
    this(id, new ICharm[0]);
  }

  public DummyCharm(String id, ICharm[] parents) {
    this(id, parents, new GenericTrait[0]);
  }

  public DummyCharm(String id, ICharm[] parents, GenericTrait[] prerequisites) {
    super(id);
    this.parentCharms = new LinkedHashSet<>();
    Collections.addAll(parentCharms, parents);
    this.prerequisites = prerequisites;
  }

  @Override
  public void accept(IMagicVisitor visitor) {
    visitor.visitCharm(this);
  }

  public void addAttributeRequirement(IndirectCharmRequirement requirement) {
    requirements.add(requirement);
  }

  public void addLearnFollowUpCharm(ICharm charm) {
    learnFollowUpCharms.add(charm);
  }

  @Override
  public IndirectCharmRequirement[] getAttributeRequirements() {
    return requirements.toArray(new IndirectCharmRequirement[requirements.size()]);
  }

  @Override
  public ICharacterType getCharacterType() {
    return characterType;
  }

  public void setCharacterType(ICharacterType type) {
    characterType = type;
  }

  @Override
  public boolean isInstanceOfGenericCharm() {
    return isGeneric;
  }

  @Override
  public List<String> getParentSubeffects() {
    return new ArrayList<>();
  }

  @Override
  public IComboRestrictions getComboRules() {
    return comboRestrictions;
  }

  @Override
  public IDuration getDuration() {
    return duration;
  }

  @Override
  public GenericTrait getEssence() {
    return null;
  }

  @Override
  public String getGroupId() {
    return groupId != null ? groupId : prerequisites[0].getType().getId();
  }

  @Override
  public Set<ICharm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator) {
    return learnFollowUpCharms;
  }

  @Override
  public Set<ICharm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    return parentCharms;
  }

  @Override
  public Set<ICharm> getLearnChildCharms() {
    return learnFollowUpCharms;
  }

  @Override
  public Set<ICharm> getParentCharms() {
    return parentCharms;
  }

  @Override
  public GenericTrait[] getPrerequisites() {
    return prerequisites;
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return prerequisites[0].getType();
  }

  @Override
  public Set<ICharm> getRenderingPrerequisiteCharms() {
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
    for (ICharmAttribute iCharmAttribute : attributes) {
      if (iCharmAttribute.getId().equals(attribute.getId())) {
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
  public boolean isBlockedByAlternative(IMagicCollection magicCollection) {
    return false;
  }

  @Override
  public Set<ICharm> getMergedCharms() {
    return new HashSet<>();
  }

  @Override
  public boolean isFavored(Hero hero) {
    if (prerequisites.length <= 0) {
      return false;
    }
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    GenericTrait trait = traitMap.getTrait(getPrimaryTraitType());
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
  public IExaltedSourceBook[] getSources() {
    return new IExaltedSourceBook[]{null};
  }

  @Override
  public IExaltedSourceBook getPrimarySource() {
    return null;
  }

  public void setCharmType(CharmType type) {
    model.setCharmType(type);
  }

  public void setDuration(IDuration duration) {
    this.duration = duration;
  }

  public void setPrerequisites(ValuedTraitType[] prerequisites) {
    this.prerequisites = prerequisites;
  }

  @Override
  public CharmTypeModel getCharmTypeModel() {
    return model;
  }

  @Override
  public ICharmAttribute[] getAttributes() {
    return attributes.toArray(new ICharmAttribute[attributes.size()]);
  }

  public void addKeyword(CharmAttribute attribute) {
    this.attributes.add(attribute);
  }
}