package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.xml.GenericCharacterTemplate;
import net.sf.anathema.character.main.xml.abilitygroup.GenericGroupedTraitTypeProvider;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.main.xml.trait.pool.GenericTraitTemplatePool;

public interface ICharacterTemplateRegistryCollection {

  IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry();

  IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> getTraitGroupTemplateRegistry();

  IXmlTemplateRegistry<GenericCreationPoints> getCreationPointTemplateRegistry();

  IXmlTemplateRegistry<GenericBonusPointCosts> getBonusPointTemplateRegistry();

  IXmlTemplateRegistry<GenericExperiencePointCosts> getExperienceTemplateRegistry();

  IXmlTemplateRegistry<GenericTraitTemplateFactory> getTraitFactoryRegistry();

  IXmlTemplateRegistry<GenericTraitTemplatePool> getTraitTemplatePoolRegistry();

  IXmlTemplateRegistry<GenericPresentationTemplate> getPresentationTemplateRegistry();
}