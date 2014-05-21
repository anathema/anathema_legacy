package net.sf.anathema.hero.traits.model.trait.template;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplate;

public class TraitTemplateMapImpl implements TraitTemplateMap {


  private GroupedTraitsTemplate template;

  public TraitTemplateMapImpl(GroupedTraitsTemplate template) {
    this.template = template;
  }

  @Override
  public TraitTemplate getTemplate(TraitType type) {
    return template.standard;
  }
}
