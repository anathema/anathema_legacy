package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData, Hero {

  ICharacterModelContext getCharacterContext();

  IExtendedConfiguration getExtendedConfiguration();
}
