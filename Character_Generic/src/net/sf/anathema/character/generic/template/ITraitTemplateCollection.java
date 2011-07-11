package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitTemplateCollection {

  public ITraitTemplate getTraitTemplate(ITraitType traitType);
  
  public ITraitTemplate getDefaultTraitTemplate(ITraitType traitType);
}