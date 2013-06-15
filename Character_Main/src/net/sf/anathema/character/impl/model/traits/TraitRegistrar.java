package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public interface TraitRegistrar {

  void addTraits(ICoreTraitConfiguration configuration, ICharacterTemplate template);

  void initListening(ICoreTraitConfiguration traitConfiguration, CharacterListening listening);
}
