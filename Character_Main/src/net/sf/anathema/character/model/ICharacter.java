package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.combos.CombosModel;
import net.sf.anathema.character.main.model.spells.SpellModel;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData, Hero {

  CharmsModel getCharms();

  CombosModel getCombos();

  SpellModel getSpells();

  ICharacterModelContext getCharacterContext();

  IExtendedConfiguration getExtendedConfiguration();

  HeroTemplate getTemplate();

  ICharacterType getCharacterType();
}
