package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitTemplateCollection {

  ITraitTemplate getTraitTemplate(ITraitType traitType);
  
  ITraitTemplateFactory getTraitTemplateFactory();
}