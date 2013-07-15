package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;

public interface ITraitTemplateCollection {

  ITraitTemplate getTraitTemplate(TraitType traitType);
  
  ITraitTemplateFactory getTraitTemplateFactory();
}