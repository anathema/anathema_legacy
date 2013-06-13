package net.sf.anathema.character.attributes.sheet.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class AttributesContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public AttributesContent(IGenericCharacter character, Resources resources) {
    super(resources);
    this.character = character;
  }

  public int getTraitMax() {
    ITraitType traitType = getGroupedTraitTypes()[0].getTraitType();
    ITraitTemplate template = getTraitTemplateCollection().getTraitTemplate(traitType);
    return template.getLimitation().getAbsoluteLimit(character);
  }

  private ITraitTemplateCollection getTraitTemplateCollection() {
    return character.getTemplate().getTraitTemplateCollection();
  }

  private GroupedTraitType[] getGroupedTraitTypes() {
    return character.getTemplate().getAttributeGroups();
  }

  @Override
  public String getHeaderKey() {
    return "Attributes";
  }

  public List<PrintAttributeGroup> getAttributeGroups() {
    List<PrintAttributeGroup> groups = new ArrayList<>();
    for (String groupedId : getGroupedIds()) {
      PrintAttributeGroup group = new PrintAttributeGroup();
      group.attributes.addAll(getPrintAttributes(groupedId));
      groups.add(group);
    }
    return groups;
  }

  private List<PrintAttribute> getPrintAttributes(String groupId) {
    IGenericTraitCollection traitCollection = character.getTraitCollection();
    List<PrintAttribute> attributes = new ArrayList<>();
    for (GroupedTraitType groupedType : getGroupedTraitTypes()) {
      if (groupedType.getGroupId().equals(groupId)) {
        PrintAttribute attribute = new PrintAttribute();
        attribute.name = getResources().getString("AttributeType.Name." + groupedType.getTraitType().getId());
        attribute.value = traitCollection.getTrait(groupedType.getTraitType()).getCurrentValue();
        attributes.add(attribute);
      }
    }
    return attributes;
  }

  private List<String> getGroupedIds() {
    List<String> groupIdList = new ArrayList<>();
    for (GroupedTraitType type : getGroupedTraitTypes()) {
      if (!groupIdList.contains(type.getGroupId())) {
        groupIdList.add(type.getGroupId());
      }
    }
    return groupIdList;
  }
}
