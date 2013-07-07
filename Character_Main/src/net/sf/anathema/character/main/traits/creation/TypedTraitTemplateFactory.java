package net.sf.anathema.character.main.traits.creation;

import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;

public interface TypedTraitTemplateFactory {

  ITraitTemplate create(TraitType type);
}