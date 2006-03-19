package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.framework.xml.abilitygroup.GenericGroupedTraitTypeProvider;
import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.generic.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.generic.framework.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.generic.framework.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.generic.framework.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericCharmPresentationProperties;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.rules.GenericAdditionalRules;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePool;

public interface ICharacterTemplateRegistryCollection {

  public abstract IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> getTraitGroupTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericEssenceTemplate> getEssenceTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericCreationPoints> getCreationPointTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericBonusPointCosts> getBonusPointTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericExperiencePointCosts> getExperienceTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericTraitTemplateFactory> getTraitFactoryRegistry();

  public abstract IXmlTemplateRegistry<GenericTraitTemplatePool> getTraitTemplatePoolRegistry();

  public abstract IXmlTemplateRegistry<GenericMagicTemplate> getMagicTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericPresentationTemplate> getPresentationTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericCharmPresentationProperties> getCharmPresentationTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericHealthTemplate> getHealthTemplateRegistry();

  public abstract IXmlTemplateRegistry<GenericAdditionalRules> getAdditionalRulesRegistry();
}