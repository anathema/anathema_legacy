package net.sf.anathema.character.impl.model.temporary;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.impl.model.traits.creation.AttributeTypeGroupFactory;

public class InternalAttributeConfiguration implements AttributeConfiguration {

  private ICharacterTemplate template;
  private final IIdentifiedCasteTraitTypeGroup[] attributeTraitGroups;

  public InternalAttributeConfiguration(ICharacterTemplate template) {
    this.template = template;
    this.attributeTraitGroups = new AttributeTypeGroupFactory().createTraitGroups(template.getCasteCollection(), template.getAttributeGroups());
  }

}
