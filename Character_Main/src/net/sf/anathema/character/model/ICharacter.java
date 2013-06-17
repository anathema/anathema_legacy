package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.essencepool.model.EssencePoolModel;
import net.sf.anathema.character.main.model.Hero;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData, Hero {

  EssencePoolModel getEssencePool();

  IHealthConfiguration getHealth();

  ICharmConfiguration getCharms();

  IComboConfiguration getCombos();

  ISpellConfiguration getSpells();

  ICharacterModelContext getCharacterContext();

  IExtendedConfiguration getExtendedConfiguration();

  HeroTemplate getHeroTemplate();

  ICharacterType getCharacterType();
}
