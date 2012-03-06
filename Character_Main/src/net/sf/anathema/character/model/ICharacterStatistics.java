package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.model.traits.essence.IEssencePoolConfiguration;

public interface ICharacterStatistics {

  public ICoreTraitConfiguration getTraitConfiguration();

  public ICharacterConcept getCharacterConcept();

  public ICharacterTemplate getCharacterTemplate();

  public IEssencePoolConfiguration getEssencePool();

  public IExperiencePointConfiguration getExperiencePoints();

  public IExtendedConfiguration getExtendedConfiguration();

  public IHealthConfiguration getHealth();

  public IExaltedRuleSet getRules();

  public boolean isExperienced();

  public void setExperienced(boolean experienced);

  // todo vom (24.12.2005) (sieroux): IMagicConfiguration herausziehen 
  public ICharmConfiguration getCharms();

  public IComboConfiguration getCombos();

  public ISpellConfiguration getSpells();

  public ICharacterModelContext getCharacterContext();
}