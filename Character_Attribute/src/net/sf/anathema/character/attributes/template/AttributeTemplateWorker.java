package net.sf.anathema.character.attributes.template;

import net.sf.anathema.character.trait.IdGroups;
import net.sf.anathema.character.trait.TraitLearnValues;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class AttributeTemplateWorker {

  private AttributeTemplate template;

  public AttributeTemplateWorker(AttributeTemplate template) {
    this.template = template;
  }

  public List<TraitLearnValues> createLearnTraits() {
    List<TraitLearnValues> traitLearnValues = new ArrayList<>();
    for (Identified traitId : createAllTraitIds()) {
      traitLearnValues.add(new TraitLearnValues(traitId, template.startValue));
    }
    return traitLearnValues;
  }

  public List<IdGroups> createGroups() {
    List<IdGroups> idGroups = new ArrayList<>();
    for (AttributeGroup attributeGroup : template.groups) {
      Identifier groupId = new Identifier(attributeGroup.id);
      List<Identified> traitIds = createTraitIds(attributeGroup);
      idGroups.add(new IdGroups(groupId, traitIds));
    }
    return idGroups;
  }

  private List<Identified> createAllTraitIds() {
    List<Identified> attributeIds = new ArrayList<>();
    for (AttributeGroup attributeGroup : template.groups) {
      attributeIds.addAll(createTraitIds(attributeGroup));
    }
    return attributeIds;
  }

  private List<Identified> createTraitIds(AttributeGroup attributeGroup) {
    List<Identified> attributeIds = new ArrayList<>();
    for (String idString : attributeGroup.attributes) {
      attributeIds.add(new Identifier(idString));
    }
    return attributeIds;
  }
}
