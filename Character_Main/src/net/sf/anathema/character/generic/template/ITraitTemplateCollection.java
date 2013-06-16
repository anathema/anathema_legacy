package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;

public interface ITraitTemplateCollection {

  ITraitTemplate getTraitTemplate(TraitType traitType);
  
  ITraitTemplateFactory getTraitTemplateFactory();
}