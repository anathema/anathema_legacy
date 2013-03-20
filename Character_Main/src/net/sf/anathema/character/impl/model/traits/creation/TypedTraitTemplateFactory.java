package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface TypedTraitTemplateFactory {

  ITraitTemplate create(ITraitType type);
}