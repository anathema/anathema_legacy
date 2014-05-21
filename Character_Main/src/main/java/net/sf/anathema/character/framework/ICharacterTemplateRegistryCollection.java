package net.sf.anathema.character.framework;

import net.sf.anathema.character.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.framework.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.framework.xml.registry.IXmlTemplateRegistry;

public interface ICharacterTemplateRegistryCollection {

  IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry();

  IXmlTemplateRegistry<GenericCreationPoints> getCreationPointTemplateRegistry();

  IXmlTemplateRegistry<GenericBonusPointCosts> getBonusPointTemplateRegistry();

  IXmlTemplateRegistry<GenericExperiencePointCosts> getExperienceTemplateRegistry();
}