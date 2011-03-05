package net.sf.anathema.character.generic.impl.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public abstract class AbstractCharacterTemplate implements ICharacterTemplate {

  private NullAdditionalRules additionalRules;

  public AbstractCharacterTemplate() {
    this.additionalRules = new NullAdditionalRules();
  }

  public IAdditionalRules getAdditionalRules() {
    return additionalRules;
  }

  public final ICasteCollection getCasteCollection() {
    return new CasteCollection(getAllCasteTypes());
  }

  protected abstract ICasteType[] getAllCasteTypes();

  public ITraitType getToughnessControllingTraitType() {
    return AbilityType.Endurance;
  }

  public IAdditionalTemplate[] getAdditionalTemplates() {
    return new IAdditionalTemplate[0];
  }

  public IExaltedEdition getEdition() {
    return ExaltedEdition.FirstEdition;
  }

  public IGroupedTraitType[] getAttributeGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AttributeType.Strength, AttributeGroupType.Physical.getId(), null),
        new GroupedTraitType(AttributeType.Dexterity, AttributeGroupType.Physical.getId(), null),
        new GroupedTraitType(AttributeType.Stamina, AttributeGroupType.Physical.getId(), null),
        new GroupedTraitType(AttributeType.Charisma, AttributeGroupType.Social.getId(), null),
        new GroupedTraitType(AttributeType.Manipulation, AttributeGroupType.Social.getId(), null),
        new GroupedTraitType(AttributeType.Appearance, AttributeGroupType.Social.getId(), null),
        new GroupedTraitType(AttributeType.Perception, AttributeGroupType.Mental.getId(), null),
        new GroupedTraitType(AttributeType.Intelligence, AttributeGroupType.Mental.getId(), null),
        new GroupedTraitType(AttributeType.Wits, AttributeGroupType.Mental.getId(), null), };
  }
  
  public boolean isLegacy()
  {
	  return false;
  }
}