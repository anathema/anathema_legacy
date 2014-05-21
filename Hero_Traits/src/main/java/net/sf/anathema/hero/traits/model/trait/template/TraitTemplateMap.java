package net.sf.anathema.hero.traits.model.trait.template;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.template.TraitTemplate;

public interface TraitTemplateMap {

  TraitTemplate getTemplate(TraitType type);
}