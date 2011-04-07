package net.sf.anathema.character.infernal;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.type.CharacterType;

import net.sf.anathema.character.infernal.caste.InfernalCaste;

public class InfernalCharacterModule extends NullObjectCharacterModuleAdapter {

	  @Override
	  public void registerCommonData(ICharacterGenerics characterGenerics) {
	    characterGenerics.getCasteCollectionRegistry().register(
	        CharacterType.INFERNAL,
	        new CasteCollection(InfernalCaste.values()));
	  }
  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Infernal2nd.template"); //$NON-NLS-1$
  }

}