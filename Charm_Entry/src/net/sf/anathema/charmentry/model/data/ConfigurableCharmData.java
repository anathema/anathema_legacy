package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConfigurableCharmData implements IConfigurableCharmData {

  private ICharacterType characterType;
  private String id;
  private boolean generic;
  private IDuration duration;
  private IGenericTrait essence = new ValuedTraitType(OtherTraitType.Essence, 1);
  private Map<ITraitType, IGenericTrait> prerequisitesByType = new LinkedHashMap<ITraitType, IGenericTrait>();
  private String groupId;
  private final Set<ICharm> parentCharms = new ListOrderedSet<ICharm>();
  private final ITextualDescription name = new SimpleTextualDescription(""); //$NON-NLS-1$
  private final IConfigurableCostList temporaryCost = new ConfigurableCostList();
  private IExaltedSourceBook source = null;
  private ITraitType primaryType;
  private final List<ICharmAttribute> keywords = new ArrayList<ICharmAttribute>();
  private final CharmTypeModel model = new CharmTypeModel();
  private boolean excellencyRequired;
  private int page;

  @Override
  public void setCharacterType(ICharacterType type) {
    this.characterType = type;
  }

  @Override
  public ICharacterType getCharacterType() {
    return characterType;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
  
  public void setGeneric(boolean generic) {
	this.generic = generic;
  }
  
  @Override
  public boolean isInstanceOfGenericCharm()
  {
	return generic;
  }

  @Override
  public IExaltedSourceBook[] getSources() {
    return new IExaltedSourceBook[] { source };
  }
  
  @Override
  public IExaltedSourceBook getPrimarySource() {
	return source;
  }

  @Override
  public void setSource(IExaltedSourceBook source) {
    this.source = source;
  }

  @Override
  public IConfigurableCostList getTemporaryCost() {
    return temporaryCost;
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
  public IGenericTrait[] getPrerequisites() {
    Collection<IGenericTrait> values = prerequisitesByType.values();
    return values.toArray(new IGenericTrait[values.size()]);
  }

  @Override
  public String getGroupId() {
    return groupId;
  }

  @Override
  public Set<ICharm> getParentCharms() {
    return parentCharms;
  }

  @Override
  public void setDuration(IDuration duration) {
    this.duration = duration;
  }

  @Override
  public void setEssencePrerequisite(IGenericTrait prerequisite) {
    this.essence = prerequisite;
  }

  public void addPrerequisite(IGenericTrait prerequisite) {
    prerequisitesByType.put(prerequisite.getType(), prerequisite);
  }

  @Override
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

  @Override
  public ITextualDescription getName() {
    return name;
  }
  
  @Override
  public List<String> getParentSubeffects() {
	    throw new NotYetImplementedException();
	  }

  @Override
  public IComboRestrictions getComboRules() {
    throw new NotYetImplementedException();
  }

  @Override
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

  @Override
  public ITraitType getPrimaryTraitType() {
    return primaryType;
  }

  public void clearPrimaryPrerequisite() {
    setPrimaryPrerequisite(ValuedTraitType.NULL_TYPE);
  }

  @Override
  public void setParentCharms(ICharm[] charms) {
    parentCharms.clear();
    Collections.addAll(parentCharms, charms);
  }

  @Override
  public void addAttribute(ICharmAttribute charmAttribute) {
    keywords.add(charmAttribute);
  }

  @Override
  public void removeAttribute(ICharmAttribute charmAttribute) {
    keywords.remove(charmAttribute);
  }

  @Override
  public ICharmAttribute[] getAttributes() {
    return keywords.toArray(new ICharmAttribute[keywords.size()]);
  }

  @Override
  public CharmTypeModel getCharmTypeModel() {
    return model;
  }

  @Override
  public void setExcellencyRequired(boolean required) {
    this.excellencyRequired = required;
  }

  @Override
  public ICharmAttributeRequirement[] getAttributeRequirements() {
    if (!excellencyRequired) {
      return new ICharmAttributeRequirement[0];
    }
    return new ICharmAttributeRequirement[] { new CharmAttributeRequirement(new CharmAttribute("Excellency" //$NON-NLS-1$
        + primaryType.getId(), false), 1) };
  }

  @Override
  public int getPage() {
    return page;
  }

  @Override
  public void setPage(int page) {
    this.page = page;
  }

  @Override
  public IGenericTrait getPrimaryPrerequisite() {
    return prerequisitesByType.get(primaryType);
  }
}