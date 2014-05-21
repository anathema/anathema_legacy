package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.xml.GenericCharacterTemplate;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;

public interface ICharacterTemplateRegistryCollection {

  IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry();

  IXmlTemplateRegistry<GenericCreationPoints> getCreationPointTemplateRegistry();

  IXmlTemplateRegistry<GenericBonusPointCosts> getBonusPointTemplateRegistry();

  IXmlTemplateRegistry<GenericExperiencePointCosts> getExperienceTemplateRegistry();

  IXmlTemplateRegistry<GenericPresentationTemplate> getPresentationTemplateRegistry();
}