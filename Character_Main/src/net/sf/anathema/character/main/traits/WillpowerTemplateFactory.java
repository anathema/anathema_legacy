package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;

public class WillpowerTemplateFactory implements TypedTraitTemplateFactory {
  private ITraitTemplateFactory traitTemplateFactory;

  public WillpowerTemplateFactory(ITraitTemplateFactory traitTemplateFactory) {
    this.traitTemplateFactory = traitTemplateFactory;
  }

  @Override
  public ITraitTemplate create(TraitType type) {
    return traitTemplateFactory.createWillpowerTemplate();
  }
}