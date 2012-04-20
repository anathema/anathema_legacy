package net.sf.anathema.dummy.character.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;

public class DummyCharm extends Identificate implements ICharm {

  private ValuedTraitType essence;
  private IDuration duration;
  private IComboRestrictions comboRestrictions = new ComboRestrictions();
  private IGenericTrait[] prerequisites;
  private Set<ICharm> parentCharms;
  private Set<ICharm> learnFollowUpCharms = new HashSet<ICharm>();
  private List<ICharmAttributeRequirement> requirements = new ArrayList<ICharmAttributeRequirement>();
  private ICharacterType characterType;
  private String groupId;
  private IExaltedSourceBook source;
  private CostList temporaryCost;
  private CharmTypeModel model = new CharmTypeModel();
  private List<ICharmAttribute> attributes = new ArrayList<ICharmAttribute>();

  public DummyCharm(
    String duration,
    CharmType charmType,
    IComboRestrictions comboRestrictions,
    IGenericTrait[] prerequisites) {
    super("DummyCharmDefaultId"); //$NON-NLS-1$
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
    this(id, parents, new IGenericTrait[0]);
  }

  public DummyCharm(String id, ICharm[] parents, IGenericTrait[] prerequisites) {
    super(id);
    this.parentCharms = new ListOrderedSet<ICharm>();
    Collections.addAll(parentCharms, parents);
    this.prerequisites = prerequisites;
  }

  @Override
  public void accept(IMagicVisitor visitor) {
    visitor.visitCharm(this);
  }

  public void addAttributeRequirement(ICharmAttributeRequirement requirement) {
    requirements.add(requirement);
  }

  public void addLearnFollowUpCharm(ICharm charm) {
    learnFollowUpCharms.add(charm);
  }

  @Override
  public ICharmAttributeRequirement[] getAttributeRequirements() {
    return requirements.toArray(new ICharmAttributeRequirement[requirements.size()]);
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
	return false;
  }

  @Override
  public List<String> getParentSubeffects() {
    return new ArrayList<String>();
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
  public IGenericTrait getEssence() {
    return essence;
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
  public IGenericTrait[] getPrerequisites() {
    return prerequisites;
  }

  @Override
  public ITraitType getPrimaryTraitType() {
    return prerequisites[0].getType();
  }

  @Override
  public Set<ICharm> getRenderingPrerequisiteCharms() {
    return parentCharms;
  }

  @Override
  public Set<String> getRenderingPrerequisiteLabels() {
    return emptySet();
  }

  @Override
  public CostList getTemporaryCost() {
    return temporaryCost;
  }

  public void setTemporaryCost(CostList list) {
    temporaryCost = list;
  }

  @Override
  public boolean hasAttribute(IIdentificate attribute) {
    return false;
  }

  @Override
  public String getAttributeValue(IIdentificate attribute) {
    return null;
  }

  @Override
  public boolean isBlockedByAlternative(IMagicCollection magicCollection) {
    return false;
  }

  @Override
  public Set<ICharm> getMergedCharms() {
    return new HashSet<ICharm>();
  }

  @Override
  public boolean isFavored(IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    if (prerequisites.length <= 0) {
      return false;
    }
    IGenericTrait trait = traitCollection.getTrait(getPrimaryTraitType());
    return trait instanceof IFavorableGenericTrait && ((IFavorableGenericTrait) trait).isCasteOrFavored();
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
    return new IExaltedSourceBook[] { source };
  }
  
  @Override
  public IExaltedSourceBook getPrimarySource() {
	return source;
  }

  public void setSource(IExaltedSourceBook source) {
    this.source = source;
  }

  public void setCharmType(CharmType type) {
    model.setCharmType(type);
  }

  public void setDuration(IDuration duration) {
    this.duration = duration;
  }

  public void setEssence(ValuedTraitType essence) {
    this.essence = essence;
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

  @Override
  public boolean hasChildren() {
    return false;
  }
}