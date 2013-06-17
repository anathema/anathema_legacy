package net.sf.anathema.hero.attributes.template;

import net.sf.anathema.character.main.traits.model.IdGroups;
import net.sf.anathema.character.main.traits.model.TraitLearnValues;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class AttributeTemplateWorker {

  private AttributeTemplate template;

  public AttributeTemplateWorker(AttributeTemplate template) {
    this.template = template;
  }

  public List<TraitLearnValues> createLearnTraits() {
    List<TraitLearnValues> traitLearnValues = new ArrayList<>();
    for (Identifier traitId : createAllTraitIds()) {
      traitLearnValues.add(new TraitLearnValues(traitId, template.startValue));
    }
    return traitLearnValues;
  }

  public List<IdGroups> createGroups() {
    List<IdGroups> idGroups = new ArrayList<>();
    for (AttributeGroup attributeGroup : template.groups) {
      SimpleIdentifier groupId = new SimpleIdentifier(attributeGroup.id);
      List<Identifier> traitIds = createTraitIds(attributeGroup);
      idGroups.add(new IdGroups(groupId, traitIds));
    }
    return idGroups;
  }

  private List<Identifier> createAllTraitIds() {
    List<Identifier> attributeIds = new ArrayList<>();
    for (AttributeGroup attributeGroup : template.groups) {
      attributeIds.addAll(createTraitIds(attributeGroup));
    }
    return attributeIds;
  }

  private List<Identifier> createTraitIds(AttributeGroup attributeGroup) {
    List<Identifier> attributeIds = new ArrayList<>();
    for (String idString : attributeGroup.attributes) {
      attributeIds.add(new SimpleIdentifier(idString));
    }
    return attributeIds;
  }
}
