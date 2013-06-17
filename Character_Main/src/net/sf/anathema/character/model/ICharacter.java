package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.abilities.AbilityModel;
import net.sf.anathema.character.main.attributes.model.temporary.AttributeModel;
import net.sf.anathema.character.main.essencepool.model.EssencePoolModel;
import net.sf.anathema.character.main.model.Hero;
import net.sf.anathema.character.main.traits.model.TraitModel;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData, Hero {

  AttributeModel getAttributes();

  AbilityModel getAbilities();

  EssencePoolModel getEssencePool();

  IHealthConfiguration getHealth();

  ICharmConfiguration getCharms();

  IComboConfiguration getCombos();

  ISpellConfiguration getSpells();

  ICharacterModelContext getCharacterContext();

  TraitModel getTraitModel();

  IExtendedConfiguration getExtendedConfiguration();

  HeroTemplate getHeroTemplate();

  ICharacterType getCharacterType();
}
