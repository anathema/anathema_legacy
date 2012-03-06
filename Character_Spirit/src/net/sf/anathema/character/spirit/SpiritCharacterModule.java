package net.sf.anathema.character.spirit;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;

@CharacterModule
public class SpiritCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Spirit2nd.template"); //$NON-NLS-1$
  }
}