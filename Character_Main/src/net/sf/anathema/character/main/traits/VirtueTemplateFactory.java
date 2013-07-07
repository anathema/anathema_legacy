package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;

public class VirtueTemplateFactory implements TypedTraitTemplateFactory {
  private ITraitTemplateFactory traitTemplateFactory;

  public VirtueTemplateFactory(ITraitTemplateFactory traitTemplateFactory) {
    this.traitTemplateFactory = traitTemplateFactory;
  }

  @Override
  public ITraitTemplate create(TraitType type) {
    return traitTemplateFactory.createVirtueTemplate((VirtueType) type);
  }
}