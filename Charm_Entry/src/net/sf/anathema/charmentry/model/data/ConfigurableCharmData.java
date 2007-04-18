package net.sf.anathema.charmentry.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class ConfigurableCharmData implements IConfigurableCharmData {

  private ICharacterType characterType;
  private String id;
  private IDuration duration;
  private IGenericTrait essence = new ValuedTraitType(OtherTraitType.Essence, 1);
  private Map<ITraitType, IGenericTrait> prerequisitesByType = new LinkedHashMap<ITraitType, IGenericTrait>();
  private String groupId;
  private final Set<ICharm> parentCharms = new ListOrderedSet<ICharm>();
  private final ITextualDescription name = new SimpleTextualDescription(""); //$NON-NLS-1$
  private final IConfigurableCostList temporaryCost = new ConfigurableCostList();
  private IExaltedSourceBook source = null;
  private ITraitType primaryType;
  private IExaltedEdition edition;
  private final List<ICharmAttribute> keywords = new ArrayList<ICharmAttribute>();
  private final CharmTypeModel model = new CharmTypeModel();
  private boolean excellencyRequired;
  private int page;

  public void setCharacterType(ICharacterType type) {
    this.characterType = type;
  }

  public ICharacterType getCharacterType() {
    return characterType;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public IExaltedSourceBook getSource() {
    return source;
  }

  public void setSource(IExaltedSourceBook source) {
    this.source = source;
  }

  public IConfigurableCostList getTemporaryCost() {
    return temporaryCost;
  }

  public IDuration getDuration() {
    return duration;
  }

  public IGenericTrait getEssence() {
    return essence;
  }

  public IGenericTrait[] getPrerequisites() {
    return prerequisitesByType.values().toArray(new IGenericTrait[0]);
  }

  private IGenericTrait getPrerequisiteByType(ITraitType type) {
    return prerequisitesByType.get(type);
  }

  public String getGroupId() {
    return groupId;
  }

  public Set<ICharm> getParentCharms() {
    return parentCharms;
  }

  public void setDuration(IDuration duration) {
    this.duration = duration;
  }

  public void setEssencePrerequisite(IGenericTrait prerequisite) {
    this.essence = prerequisite;
  }

  public void addPrerequisite(IGenericTrait prerequisite) {
    prerequisitesByType.put(prerequisite.getType(), prerequisite);
  }

  public void setGroupId(String id) {
    this.groupId = id;
  }

  public void removePrerequisite(IGenericTrait unwanted) {
    ITraitType type = unwanted.getType();
    if (type == primaryType) {
      throw new IllegalArgumentException("Must not remove primary prerequisite!"); //$NON-NLS-1$
    }
    removePrerequisiteByType(type);
  }

  private void removePrerequisiteByType(ITraitType type) {
    prerequisitesByType.remove(type);
  }

  public ITextualDescription getName() {
    return name;
  }

  public IComboRestrictions getComboRules() {
    throw new NotYetImplementedException();
  }

  public void setPrimaryPrerequisite(IGenericTrait prerequisite) {
    removePrerequisiteByType(primaryType);
    this.primaryType = prerequisite.getType();
    if (prerequisite.getType() != null) {
      LinkedHashMap<ITraitType, IGenericTrait> map = new LinkedHashMap<ITraitType, IGenericTrait>();
      map.put(primaryType, prerequisite);
      map.putAll(prerequisitesByType);
      prerequisitesByType = map;
    }
  }

  public ITraitType getPrimaryPrerequisiteType() {
    return primaryType;
  }

  public void clearPrimaryPrerequisite() {
    setPrimaryPrerequisite(ValuedTraitType.NULL_TYPE);
  }

  public IGenericTrait getPrimaryPrerequiste() {
    return getPrerequisiteByType(primaryType);
  }

  public void setParentCharms(ICharm[] charms) {
    parentCharms.clear();
    Collections.addAll(parentCharms, charms);
  }

  public void setEdition(IExaltedEdition edition) {
    this.edition = edition;
  }

  public IExaltedEdition getEdition() {
    return edition;
  }

  public void addAttribute(ICharmAttribute charmAttribute) {
    keywords.add(charmAttribute);
  }

  public void removeAttribute(ICharmAttribute charmAttribute) {
    keywords.remove(charmAttribute);
  }

  public ICharmAttribute[] getAttributes() {
    return keywords.toArray(new ICharmAttribute[keywords.size()]);
  }

  public CharmTypeModel getCharmTypeModel() {
    return model;
  }

  public void setExcellencyRequired(boolean required) {
    this.excellencyRequired = required;
  }

  public ICharmAttributeRequirement[] getAttributeRequirements() {
    if (!excellencyRequired) {
      return new ICharmAttributeRequirement[0];
    }
    return new ICharmAttributeRequirement[] { new CharmAttributeRequirement(new CharmAttribute("Excellency" //$NON-NLS-1$
        + primaryType.getId(), false), 1) };
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }
}