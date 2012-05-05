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

  IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry();

  IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> getTraitGroupTemplateRegistry();

  IXmlTemplateRegistry<GenericEssenceTemplate> getEssenceTemplateRegistry();

  IXmlTemplateRegistry<GenericCreationPoints> getCreationPointTemplateRegistry();

  IXmlTemplateRegistry<GenericBonusPointCosts> getBonusPointTemplateRegistry();

  IXmlTemplateRegistry<GenericExperiencePointCosts> getExperienceTemplateRegistry();

  IXmlTemplateRegistry<GenericTraitTemplateFactory> getTraitFactoryRegistry();

  IXmlTemplateRegistry<GenericTraitTemplatePool> getTraitTemplatePoolRegistry();

  IXmlTemplateRegistry<GenericMagicTemplate> getMagicTemplateRegistry();

  IXmlTemplateRegistry<GenericPresentationTemplate> getPresentationTemplateRegistry();

  IXmlTemplateRegistry<GenericHealthTemplate> getHealthTemplateRegistry();

  IXmlTemplateRegistry<GenericAdditionalRules> getAdditionalRulesRegistry();
}