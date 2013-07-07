package net.sf.anathema.character.main.traits.creation;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;

public interface TypedTraitTemplateFactory {

  ITraitTemplate create(TraitType type);
}