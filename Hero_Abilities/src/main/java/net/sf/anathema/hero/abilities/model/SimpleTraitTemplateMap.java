package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.traits.model.TraitTemplateMap;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplate;

public class SimpleTraitTemplateMap implements TraitTemplateMap {


  private GroupedTraitsTemplate template;

  public SimpleTraitTemplateMap(GroupedTraitsTemplate template) {
    this.template = template;
  }

  @Override
  public TraitTemplate getTemplate(TraitType type) {
    return template.standard;
  }
}
