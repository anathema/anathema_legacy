package net.sf.anathema.character.mortal;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;

@CharacterModule
public class MortalCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "HeroicMortal.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "HeroicMortal2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "Mortal.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "Mortal2nd.template"); //$NON-NLS-1$
  }
}