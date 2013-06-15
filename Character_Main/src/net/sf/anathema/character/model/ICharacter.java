package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.model.traits.essence.IEssencePoolConfiguration;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData, Hero {

  IEssencePoolConfiguration getEssencePool();

  IHealthConfiguration getHealth();

  ICharmConfiguration getCharms();

  IComboConfiguration getCombos();

  ISpellConfiguration getSpells();

  ICharacterModelContext getCharacterContext();

  ICoreTraitConfiguration getTraitConfiguration();

  IExtendedConfiguration getExtendedConfiguration();

  ICharacterTemplate getCharacterTemplate();
}
