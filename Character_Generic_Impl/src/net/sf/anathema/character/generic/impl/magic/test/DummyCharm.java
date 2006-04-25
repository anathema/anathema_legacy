package net.sf.anathema.character.generic.impl.magic.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.MagicSource;
import net.sf.anathema.character.generic.impl.magic.PermanentCostList;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DummyCharm extends Identificate implements ICharm {

  private ValuedTraitType essence;
  private Duration duration;
  private CharmType charmType;
  private IComboRestrictions comboRestrictions = new ComboRestrictions();
  private IGenericTrait[] prerequisites;
  private Set<ICharm> parentCharms;
  private Set<ICharm> learnFollowUpCharms = new HashSet<ICharm>();
  private List<ICharmAttributeRequirement> requirements = new ArrayList<ICharmAttributeRequirement>();
  private CharacterType characterType;
  private String groupId;
  private IMagicSource source;
  private CostList temporaryCost;

  public DummyCharm(
      DurationType durationType,
      CharmType charmType,
      IComboRestrictions comboRestrictions,
      IGenericTrait[] prerequisites) {
    super("DummyCharmDefaultId"); //$NON-NLS-1$
    this.prerequisites = prerequisites;
    this.duration = Duration.getDuration(durationType.getId());
    this.charmType = charmType;
    this.comboRestrictions = comboRestrictions;
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

  public void accept(IMagicVisitor visitor) {
    visitor.visitCharm(this);
  }

  public void addAttributeRequirement(ICharmAttributeRequirement requirement) {
    requirements.add(requirement);
  }

  public void addLearnFollowUpCharm(ICharm charm) {
    learnFollowUpCharms.add(charm);
  }

  public ICharmAttributeRequirement[] getAttributeRequirements() {
    return requirements.toArray(new ICharmAttributeRequirement[requirements.size()]);
  }

  public CharacterType getCharacterType() {
    return characterType;
  }

  public void setCharacterType(CharacterType type) {
    characterType = type;
  }

  public CharmType getCharmType() {
    return charmType;
  }

  public IComboRestrictions getComboRules() {
    return comboRestrictions;
  }

  public Duration getDuration() {
    return duration;
  }

  public IGenericTrait getEssence() {
    return essence;
  }

  public String getGroupId() {
    return groupId != null ? groupId : prerequisites[0].getType().getId();
  }

  public Set<ICharm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator) {
    return learnFollowUpCharms;
  }

  public Set<ICharm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    return parentCharms;
  }

  public Set<ICharm> getParentCharms() {
    return parentCharms;
  }

  public PermanentCostList getPermanentCost() {
    throw new NotYetImplementedException();
  }

  public IGenericTrait[] getPrerequisites() {
    return prerequisites;
  }

  public Set<ICharm> getRenderingPrerequisiteCharms() {
    return parentCharms;
  }

  public CostList getTemporaryCost() {
    return temporaryCost;
  }

  public void setTemporaryCost(CostList list) {
    temporaryCost = list;
  }

  public boolean hasAttribute(IIdentificate attribute) {
    return false;
  }

  public boolean isBlockedByAlternative(IMagicCollection magicCollection) {
    return false;
  }

  public boolean isFavored(
      IBasicCharacterData basicCharacter,
      IGenericTraitCollection traitCollection,
      FavoringTraitType type) {
    if (getPrerequisites().length <= 0) {
      return false;
    }
    IGenericTrait trait = traitCollection.getTrait(getPrerequisites()[0].getType());
    return trait instanceof IFavorableGenericTrait && ((IFavorableGenericTrait) trait).isCasteOrFavored();
  }

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

  public void setSource(MagicSource source) {
    this.source = source;
  }

  public IMagicSource getSource() {
    return source;
  }

  public void setCharmType(CharmType type) {
    charmType = type;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public void setEssence(ValuedTraitType essence) {
    this.essence = essence;
  }

  public void setPrerequisites(ValuedTraitType[] prerequisites) {
    this.prerequisites = prerequisites;
  }
}