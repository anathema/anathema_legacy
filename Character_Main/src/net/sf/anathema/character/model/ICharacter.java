package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.model.combos.CombosModel;
import net.sf.anathema.character.main.model.spells.SpellModel;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData, Hero {

  CombosModel getCombos();

  SpellModel getSpells();

  ICharacterModelContext getCharacterContext();

  IExtendedConfiguration getExtendedConfiguration();
}
