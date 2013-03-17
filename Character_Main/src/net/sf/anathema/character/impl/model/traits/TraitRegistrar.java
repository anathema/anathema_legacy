package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public interface TraitRegistrar {
  void addTraits(ICharacterTemplate template, ICoreTraitConfiguration configuration);
}
