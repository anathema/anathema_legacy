package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData, Hero {

  HealthModel getHealth();

  ICharmConfiguration getCharms();

  IComboConfiguration getCombos();

  ISpellConfiguration getSpells();

  ICharacterModelContext getCharacterContext();

  IExtendedConfiguration getExtendedConfiguration();

  HeroTemplate getHeroTemplate();

  ICharacterType getCharacterType();
}
